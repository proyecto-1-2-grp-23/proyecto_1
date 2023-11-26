import { proyectoCandidatoCrear } from './../proyectoCandidato';
import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { ServicioProyectosService } from '../Servicio/servicio-proyectos.service';
import { ServicioCandidatosService } from 'src/app/Candidatos/servicio/servicio-candidatos.service';
import Swal from 'sweetalert2';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-agregar-candidato-proyecto',
  templateUrl: './agregar-candidato-proyecto.component.html',
  styleUrls: ['./agregar-candidato-proyecto.component.css'],
})
export class AgregarCandidatoProyectoComponent implements OnInit {
  registrationForm: FormGroup = new FormGroup({
    candidatosCompatibles: new FormControl('', [Validators.required]),
    caracteristicasPersonalidad: new FormControl('', [Validators.required]),
    caracteristicasTecnicas: new FormControl('', [Validators.required]),
  });

  idProyect: any;
  proyectoCandidato!: proyectoCandidatoCrear;

  caracteristicasTecnicas: {
    Nombre: any;
  }[] = [];

  caracteristicasPersonalidad: {
    Nombre: any;
  }[] = [];

  candidatos: {
    Nombre: any;
    Id: any;
  }[] = [];

  constructor(
    @Inject(MAT_DIALOG_DATA) data: any,
    public dialogRef: MatDialogRef<AgregarCandidatoProyectoComponent>,
    private candidatoService: ServicioCandidatosService,
    private proyectoService: ServicioProyectosService
  ) {
    if (data != null) {
      let informacion = data.info;
      console.log(informacion, 'info');
      this.idProyect = informacion.id;
    }
  }

  ngOnInit(): void {
    this.llenarCaracteristicasTecnicas();
    this.llenarCaracteristicasPersonalidad();
  }

  agregar() {
    this.proyectoCandidato = {
      idProyecto: this.idProyect,
      idCandidato: parseInt(
        this.registrationForm.get('candidatosCompatibles')?.value
      ),
    };

    this.proyectoService
      .agregarCandidatoProyecto(this.proyectoCandidato)
      .subscribe(
        (res) => {
          console.log(res);
          if (res.id > 0) {
            Swal.fire('', 'Candidato Asociado', 'success');
            this.dialogRef.close();
          } else {
            Swal.fire('', 'Error en la asociación del candidato', 'error');
          }
        },
        (error: HttpErrorResponse) => {
          if (error.status == 412) {
            console.log(this.idProyect, 'info');
            Swal.fire('', 'Candidato ya existe en el proyecto ', 'error');
          } else {
            Swal.fire(
              '',
              'Error en la asociación del candidato' + error.status,
              'error'
            );
          }
          this.dialogRef.close();
        }
      );
  }

  verProyectos() {
    this.dialogRef.close();
  }

  llenarCaracteristicasTecnicas() {
    this.proyectoService
      .listarProyectosById(this.idProyect)
      .subscribe((res) => {
        console.log(res);
        res.forEach((registro: any) => {
          const nombre = registro.habilidades_blandas;
          const valores: string[] = nombre.split(',');
          for (let valor of valores) {
            const nuevoRegistro = {
              Nombre: valor,
            };
            const registroExistente = this.caracteristicasTecnicas.find(
              (registro) => registro.Nombre === nuevoRegistro.Nombre
            );
            if (!registroExistente) {
              this.caracteristicasTecnicas.push(nuevoRegistro);
            }
          }
        });
      });
  }

  llenarCaracteristicasPersonalidad() {
    this.candidatoService.obtenerCandidatos().subscribe((res) => {
      res.forEach((registro: any) => {
        const nombre = registro.rasgosPersonalidad;
        const valores: string[] = nombre.split(',');
        for (let valor of valores) {
          const nuevoRegistro = {
            Nombre: valor,
          };
          const registroExistente = this.caracteristicasPersonalidad.find(
            (registro) => registro.Nombre === nuevoRegistro.Nombre
          );
          if (!registroExistente) {
            // Si no está repetido, agregar el nuevo registro
            this.caracteristicasPersonalidad.push(nuevoRegistro);
          }
        }
      });
    });
  }

  buscarCandidatos() {
    this.candidatos = [];
    this.candidatoService
      .obtenerCandidatosHabTec(
        this.registrationForm.get('caracteristicasTecnicas')?.value
      )
      .subscribe((res) => {
        console.log(res, 'res');
        res.forEach((registro: any) => {
          this.consultarCandidato(registro.idUsuario);
        });
      });

    this.candidatoService
      .obtenerCandidatosHabPer(
        this.registrationForm.get('caracteristicasPersonalidad')?.value
      )
      .subscribe((res) => {
        res.forEach((registro: any) => {
          this.consultarCandidato(registro.idUsuario);
        });
      });
  }

  consultarCandidato(id: any) {
    this.candidatoService.obtenerCandidatoPorId(id).subscribe((res) => {
      res.forEach((registro: any) => {
        const nombre = registro.nombreCompleto;
        const id = registro.idUsuario;
        const nuevoRegistro = {
          Nombre: nombre,
          Id: id,
        };
        const registroExistente = this.candidatos.find(
          (registro) => registro.Id === nuevoRegistro.Id
        );
        if (!registroExistente) {
          // Si no está repetido, agregar el nuevo registro
          this.candidatos.push(nuevoRegistro);
        }
      });
    });
  }

  buscarRecomendado() {}
}
