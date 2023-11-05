import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';

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

  constructor(
    public dialogRef: MatDialogRef<AgregarCandidatoProyectoComponent>
  ) {}

  ngOnInit(): void {}

  agregar() {
    this.dialogRef.close();
  }

  verProyectos() {
    this.dialogRef.close();
  }
}
