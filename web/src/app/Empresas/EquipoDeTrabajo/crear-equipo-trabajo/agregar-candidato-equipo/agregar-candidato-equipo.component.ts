import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';

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

  constructor(
    public dialogRef: MatDialogRef<AgregarCandidatoEquipoComponent>
  ) {}

  ngOnInit(): void {}

  agregar() {
    this.dialogRef.close();
  }

  verEquipo() {
    this.dialogRef.close();
  }
}
