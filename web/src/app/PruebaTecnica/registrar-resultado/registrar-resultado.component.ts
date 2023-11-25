import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-registrar-resultado',
  templateUrl: './registrar-resultado.component.html',
  styleUrls: ['./registrar-resultado.component.css'],
})
export class RegistrarResultadoComponent implements OnInit {
  registrationForm: FormGroup = new FormGroup({
    proyectos: new FormControl('', [Validators.required]),
    candidatos: new FormControl('', [Validators.required]),
    pregunta1: new FormControl('', [Validators.required]),
    pregunta2: new FormControl('', [Validators.required]),
    pregunta3: new FormControl('', [Validators.required]),
    pregunta4: new FormControl('', [Validators.required]),
    pregunta5: new FormControl('', [Validators.required]),
    respuesta1: new FormControl('', [Validators.required]),
    respuesta2: new FormControl('', [Validators.required]),
    respuesta3: new FormControl('', [Validators.required]),
    respuesta4: new FormControl('', [Validators.required]),
    respuesta5: new FormControl('', [Validators.required]),
    observaciones: new FormControl('', [Validators.required]),
  });

  proyectos: {
    Nombre: any;
    Id: any;
  }[] = [];

  candidatos: {
    Nombre: any;
    Id: any;
  }[] = [];

  preguntas: {
    Nombre: any;
    Id: any;
  }[] = [];

  respuestas: string[] = ['Correcto', 'Incorrecto'];

  constructor(private router: Router) {}

  ngOnInit(): void {}

  guardar() {}

  verMenu() {
    this.router.navigate([`/menu/empresa`]);
  }
}
