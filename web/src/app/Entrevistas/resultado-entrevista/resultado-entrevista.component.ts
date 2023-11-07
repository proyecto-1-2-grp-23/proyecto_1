import { Component, OnInit, Inject } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-resultado-entrevista',
  templateUrl: './resultado-entrevista.component.html',
  styleUrls: ['./resultado-entrevista.component.css'],
})
export class ResultadoEntrevistaComponent implements OnInit {
  registrationForm: FormGroup = new FormGroup({
    estado: new FormControl('', [Validators.required]),
    descripcion: new FormControl('', [Validators.required]),
    observaciones: new FormControl('', [Validators.required]),
  });

  constructor(
    @Inject(MAT_DIALOG_DATA) data: any,
    private dialog: MatDialogRef<ResultadoEntrevistaComponent>
  ) {
    if (data != null) {
      let informacion = data.info;
      console.log(informacion, 'info');

      this.registrationForm.get('estado')?.setValue(informacion.Estado);

      this.registrationForm
        .get('descripcion')
        ?.setValue(informacion.Descripcion);

      this.registrationForm
        .get('observaciones')
        ?.setValue(informacion.Observaciones);
    }
  }

  ngOnInit(): void {}

  verEntrevistas() {
    this.dialog.close();
  }
}
