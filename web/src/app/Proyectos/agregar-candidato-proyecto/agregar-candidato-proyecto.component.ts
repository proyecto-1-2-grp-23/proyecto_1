import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { ServicioProyectosService } from '../Servicio/servicio-proyectos.service';

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
    private proyectocandidatoService: ServicioProyectosService,
    private proyectodataLaboralService: ServicioProyectosService,
  ) {
    if (data != null) {
      let informacion = data.info;
      this.idProyect = informacion.Proyecto;
    }
  }

  ngOnInit(): void {
    this.llenarCaracteristicasTecnicas();
    this.llenarCaracteristicasPersonalidad();
    this.obtenerCandidato()
  }


  agregar() {

    console.log(
      this.registrationForm.get('candidatosCompatibles')?.value,
      'candidato'
    );

    console.log(this.idProyect, 'info Proyect');
    this.dialogRef.close();
  }

  verProyectos() {
    this.dialogRef.close();
  }

  llenarCaracteristicasTecnicas() {
    this.proyectodataLaboralService.listarProyectosDataLaboral().subscribe((res) =>{
      console.log(res)
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
            this.caracteristicasTecnicas.push(nuevoRegistro);
          }
        }
      });

    });
  }

  llenarCaracteristicasPersonalidad() {
    this.proyectocandidatoService.obtenerProyectosCandidatos().subscribe((res) =>{
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

  obtenerCandidato() {
    this.proyectocandidatoService.obtenerProyectosCandidatos().subscribe((res) => {
      res.forEach((registro: any) => {
        const nombre = registro.nombreCompleto;
        const id = registro.idUsuario;
        const nuevoRegistro = {
          Nombre: nombre,
          Id: id,
        };
        const registroExistente = this.candidatos.find(
          (registro) => registro.Nombre === nuevoRegistro.Nombre
        );
        if (!registroExistente) {
          // Si no está repetido, agregar el nuevo registro
          this.candidatos.push(nuevoRegistro);
        }
      });
    });
  }

}
