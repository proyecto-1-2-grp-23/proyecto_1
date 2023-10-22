import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login-empresa',
  templateUrl: './login-empresa.component.html',
  styleUrls: ['./login-empresa.component.css'],
})
export class LoginEmpresaComponent implements OnInit {
  fieldTextType!: boolean;
  repeatFieldTextType!: boolean;

  registrationForm: FormGroup = new FormGroup({
    correo: new FormControl('', [Validators.required, Validators.email]),
    razonSocial: new FormControl('', [Validators.required]),
    tipoEmpresa: new FormControl('', [Validators.required]),
    verticales: new FormControl('', [Validators.required]),
    pais: new FormControl('', [Validators.required]),
    ciudad: new FormControl('', [Validators.required]),
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
    this.router.navigate([`/signin/empresas`]);
  }

  hacerRegistro() {
    this.router.navigate([`/signin/empresas`]);
  }
}
