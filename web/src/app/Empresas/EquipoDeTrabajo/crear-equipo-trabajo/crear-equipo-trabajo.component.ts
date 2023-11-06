import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { AgregarCandidatoEquipoComponent } from '../agregar-candidato-equipo/agregar-candidato-equipo.component';

@Component({
  selector: 'app-crear-equipo-trabajo',
  templateUrl: './crear-equipo-trabajo.component.html',
  styleUrls: ['./crear-equipo-trabajo.component.css'],
})
export class CrearEquipoTrabajoComponent implements OnInit {
  registrationForm: FormGroup = new FormGroup({
    nombreGrupo: new FormControl('', [Validators.required]),
    descripcion: new FormControl('', [Validators.required]),
    funcionarios: new FormControl('', [Validators.required]),
  });

  constructor(private router: Router, public dialog: MatDialog) {}

  ngOnInit(): void {}

  verLista() {
    this.router.navigate([`/equipoDeTrabajo`]);
  }

  guardar() {
    this.router.navigate([`/equipoDeTrabajo`]);
  }

  agregarCandidato() {
    this.dialog.open(AgregarCandidatoEquipoComponent, {
      width: '800px', // Puedes personalizar el tamaño
      height: '500px',
    });
  }
}
