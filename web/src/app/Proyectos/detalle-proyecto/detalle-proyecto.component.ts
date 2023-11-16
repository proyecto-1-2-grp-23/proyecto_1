import { Component, OnInit, Inject } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import {
  MAT_DIALOG_DATA,
  MatDialog,
  MatDialogRef,
} from '@angular/material/dialog';
import { Router } from '@angular/router';

@Component({
  selector: 'app-detalle-proyecto',
  templateUrl: './detalle-proyecto.component.html',
  styleUrls: ['./detalle-proyecto.component.css'],
})
export class DetalleProyectoComponent implements OnInit {
  chips: string[] = [];

  registrationForm: FormGroup = new FormGroup({
    nombreProyecto: new FormControl('', [Validators.required]),
    descripcion: new FormControl('', [Validators.required]),
    perfil: new FormControl('', [Validators.required]),
    conocimientos: new FormControl('', [Validators.required]),
    fechaInicio: new FormControl('', [Validators.required]),
    fechaFin: new FormControl('', [Validators.required]),
  });

  constructor(
    @Inject(MAT_DIALOG_DATA) data: any,
    private dialog: MatDialogRef<DetalleProyectoComponent>
  ) {
    if (data != null) {
      let informacion = data.info;
      console.log(informacion, 'info');

      this.registrationForm.get('nombreProyecto')?.setValue(informacion.Nombre);
      this.registrationForm
        .get('descripcion')
        ?.setValue(informacion.Descripcion);
      this.registrationForm.get('perfil')?.setValue(informacion.Perfiles);
      this.registrationForm
        .get('conocimientos')
        ?.setValue(informacion.Conocimientos);
      this.registrationForm
        .get('fechaInicio')
        ?.setValue(informacion.FechaInicio);
      this.registrationForm.get('fechaFin')?.setValue(informacion.FechaFin);

      this.chips = informacion.Habilidades.split(',');
    }
  }

  ngOnInit(): void {}

  verProyectos() {
    this.dialog.close();
  }

  verEmpleados() {}
}
