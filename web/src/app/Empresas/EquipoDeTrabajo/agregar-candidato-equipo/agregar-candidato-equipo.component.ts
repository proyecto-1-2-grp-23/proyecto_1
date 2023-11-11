import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { ServicioCandidatosService } from 'src/app/Candidatos/servicio/servicio-candidatos.service';
import { ServicioRegistroLaboralService } from 'src/app/Candidatos/servicio/servicio-registro-laboral.service';

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

  caracteristicasTecnicas: {
    Nombre: any;
  }[] = [];

  caracteristicasPersonalidad: {
    Nombre: any;
  }[] = [];

  constructor(
    public dialogRef: MatDialogRef<AgregarCandidatoEquipoComponent>,
    private dataLaboralService: ServicioRegistroLaboralService,
    private candidatoService: ServicioCandidatosService
  ) {}

  ngOnInit(): void {
    this.llenarCaracteristicasTecnicas();
    this.llenarCaracteristicasPersonalidad();
  }

  agregar() {
    this.dialogRef.close();
  }

  verEquipo() {
    this.dialogRef.close();
  }

  llenarCaracteristicasTecnicas() {
    this.dataLaboralService.listarDataLaboral().subscribe((res) => {
      console.log(res);
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
      console.log(res);
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
}
