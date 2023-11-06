import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-programar-entrevista',
  templateUrl: './programar-entrevista.component.html',
  styleUrls: ['./programar-entrevista.component.css'],
})
export class ProgramarEntrevistaComponent implements OnInit {
  fechaInicioSeleccionada: any;

  registrationForm: FormGroup = new FormGroup({
    funcionario: new FormControl('', [Validators.required]),
    empresa: new FormControl('', [Validators.required]),
    candidato: new FormControl('', [Validators.required]),
    fecha: new FormControl('', [Validators.required]),
    lugar: new FormControl('', [Validators.required]),
  });

  constructor(private router: Router) {}

  ngOnInit(): void {}

  guardar() {
    this.router.navigate([`/entrevistas`]);
  }

  cancelar() {
    this.router.navigate([`/entrevistas`]);
  }
}
