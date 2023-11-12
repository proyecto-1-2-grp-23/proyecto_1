import { Component, Inject, OnInit, ElementRef } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import {
  MAT_DIALOG_DATA,
  MatDialog,
  MatDialogRef,
} from '@angular/material/dialog';
import { ServicioCandidatosService } from 'src/app/Candidatos/servicio/servicio-candidatos.service';
import { ServicioRegistroLaboralService } from 'src/app/Candidatos/servicio/servicio-registro-laboral.service';
import { ServicioEmpresaService } from '../../servicio/servicio-empresa.service';
import { equipoCandidatoCrear } from '../equpoCandidato';
import Swal from 'sweetalert2';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-agregar-candidato-equipo',
  templateUrl: './agregar-candidato-equipo.component.html',
  styleUrls: ['./agregar-candidato-equipo.component.css'],
})
export class AgregarCandidatoEquipoComponent implements OnInit {
  registrationForm: FormGroup = new FormGroup({
    candidatosCompatibles: new FormControl('', [Validators.required]),
    caracteristicasPersonalidad: new FormControl('', [Validators.required]),
    caracteristicasTecnicas: new FormControl('', [Validators.required]),
  });

  idEquipo: any;
  equipoCandidato!: equipoCandidatoCrear;

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
    public dialogRef: MatDialogRef<AgregarCandidatoEquipoComponent>,
    private dataLaboralService: ServicioRegistroLaboralService,
    private candidatoService: ServicioCandidatosService,
    private empresaService: ServicioEmpresaService
  ) {
    if (data != null) {
      let informacion = data.info;
      this.idEquipo = informacion.Equipo;
    }
  }

  ngOnInit(): void {
    this.llenarCaracteristicasTecnicas();
    this.llenarCaracteristicasPersonalidad();
  }

  agregar() {
    console.log(
      this.registrationForm.get('candidatosCompatibles')?.value,
      'candidato'
    );

    console.log(this.idEquipo, 'info');

    this.equipoCandidato = {
      idEquipo: this.idEquipo,
      idCandidato: parseInt(
        this.registrationForm.get('candidatosCompatibles')?.value
      ),
    };

    this.empresaService.agregarCandidatoEquipo(this.equipoCandidato).subscribe(
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
          Swal.fire('', 'Candidato ya existe en el equipo ', 'error');
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

  verEquipo() {
    this.dialogRef.close();
  }

  llenarCaracteristicasTecnicas() {
    this.dataLaboralService.listarDataLaboral().subscribe((res) => {
      res.forEach((registro: any) => {
        const nombre = registro.habilidades;
        const valores: string[] = nombre.split(',');
        for (let valor of valores) {
          const nuevoRegistro = {
            Nombre: valor,
          };
          const registroExistente = this.caracteristicasTecnicas.find(
            (registro) => registro.Nombre === nuevoRegistro.Nombre
          );
          if (!registroExistente) {
            // Si no está repetido, agregar el nuevo registro
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
}
