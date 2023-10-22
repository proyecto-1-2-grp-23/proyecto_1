import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

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

  constructor(private router: Router) {}

  ngOnInit(): void {}

  verLista() {
    this.router.navigate([`/equipoDeTrabajo`]);
  }

  guardar() {
    this.router.navigate([`/equipoDeTrabajo`]);
  }
}
