import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { FormGroup, Validators, FormControl } from '@angular/forms';

@Component({
  selector: 'app-singin',
  templateUrl: './singin.component.html',
  styleUrls: ['./singin.component.css'],
})
export class SinginComponent implements OnInit {
  parametros: { tipoUsuario: string } | any;
  titulo: string = '';

  fieldTextType!: boolean;

  registrationForm: FormGroup = new FormGroup({
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', [Validators.required]),
  });

  constructor(private rutaActiva: ActivatedRoute, private router: Router) {}

  ngOnInit(): void {
    this.parametros = {
      usuario: this.rutaActiva.snapshot.params['tipoUsuario'],
    };
    this.rutaActiva.params.subscribe((params: Params) => {
      this.parametros.tipoUsuario = params['tipoUsuario'];
    });

    if (this.parametros.tipoUsuario == 'empresas') {
      this.titulo = 'EMPRESARIAL';
    } else if (this.parametros.tipoUsuario == 'candidato') {
      this.titulo = 'CANDIDATOS';
    } else {
      console.log('entro administrador');
      this.titulo = 'ADMINISTRACION';
    }
  }

  toggleFieldTextType() {
    this.fieldTextType = !this.fieldTextType;
  }

  registro() {
    if (this.parametros.tipoUsuario == 'empresas') {
      this.router.navigate([`/loginEmpresa`]);
    } else if (this.parametros.tipoUsuario == 'candidato') {
      this.router.navigate([`/loginCandidato`]);
    }
  }
}
