import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login-candidato',
  templateUrl: './login-candidato.component.html',
  styleUrls: ['./login-candidato.component.css'],
})
export class LoginCandidatoComponent implements OnInit {
  fieldTextType!: boolean;
  repeatFieldTextType!: boolean;

  registrationForm: FormGroup = new FormGroup({
    correo: new FormControl('', [Validators.required, Validators.email]),
    nombre: new FormControl('', [Validators.required]),
    edad: new FormControl('', [Validators.required]),
    telefono: new FormControl('', [Validators.required]),
    pais: new FormControl('', [Validators.required]),
    ciudad: new FormControl('', [Validators.required]),
    idiomas: new FormControl('', [Validators.required]),
    rasgos: new FormControl('', [Validators.required]),
    repContraseña: new FormControl('', [Validators.required]),
    contraseña: new FormControl('', [Validators.required]),
  });

  constructor(private router: Router) {}

  ngOnInit(): void {}

  toggleFieldTextType() {
    this.fieldTextType = !this.fieldTextType;
  }

  toggleRepeatFieldTextType() {
    this.repeatFieldTextType = !this.repeatFieldTextType;
  }

  signIn() {
    this.router.navigate([`/signin/candidato`]);
  }

  hacerRegistro() {
    this.router.navigate([`/signin/candidato`]);
  }
}
