import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-detalle-entrevista',
  templateUrl: './detalle-entrevista.component.html',
  styleUrls: ['./detalle-entrevista.component.css'],
})
export class DetalleEntrevistaComponent implements OnInit {
  registrationForm: FormGroup = new FormGroup({
    funcionario: new FormControl('', [Validators.required]),
    empresa: new FormControl('', [Validators.required]),
    candidato: new FormControl('', [Validators.required]),
    fecha: new FormControl('', [Validators.required]),
    lugar: new FormControl('', [Validators.required]),
  });

  constructor(
    @Inject(MAT_DIALOG_DATA) data: any,
    private dialog: MatDialogRef<DetalleEntrevistaComponent>
  ) {
    if (data != null) {
      let informacion = data.info;
      console.log(informacion, 'info');
    }
  }

  ngOnInit(): void {}

  verEntrevistas() {
    this.dialog.close();
  }
}
