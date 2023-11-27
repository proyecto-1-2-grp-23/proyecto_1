import { Component, OnInit, Inject } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import {
  MAT_DIALOG_DATA,
  MatDialog,
  MatDialogRef,
} from '@angular/material/dialog';
import { ListarCandidatosProyectoComponent } from '../listar-candidatos-proyecto/listar-candidatos-proyecto.component';

@Component({
  selector: 'app-detalle-proyecto',
  templateUrl: './detalle-proyecto.component.html',
  styleUrls: ['./detalle-proyecto.component.css'],
})
export class DetalleProyectoComponent implements OnInit {
  chips: string[] = [];
  idProyecto: any;

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
    private dialogOpen: MatDialog,
    private dialog: MatDialogRef<DetalleProyectoComponent>
  ) {
    if (data != null) {
      let informacion = data.info;
      console.log(informacion, 'info');
      this.idProyecto = informacion.id;

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

    this.registrationForm.disable();
  }

  ngOnInit(): void {}

  verProyectos() {
    this.dialog.close();
  }

  verEmpleados() {
    this.dialog.close();
    const dialogRef = this.dialogOpen.open(ListarCandidatosProyectoComponent, {
      width: '60%',
      height: '90%',
      data: { Id: this.idProyecto },
    });
    dialogRef.afterClosed().subscribe((result) => {});
  }
}
