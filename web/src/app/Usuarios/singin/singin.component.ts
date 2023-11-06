import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { FormGroup, Validators, FormControl } from '@angular/forms';
import { ServicioUsuariosService } from '../servicio/servicio-usuarios.service';
import { candidatoSignIn } from '../usuario';
import { HttpErrorResponse } from '@angular/common/http';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-singin',
  templateUrl: './singin.component.html',
  styleUrls: ['./singin.component.css'],
})
export class SinginComponent implements OnInit {
  parametros: { tipoUsuario: string } | any;
  titulo: string = '';

  usuario!: candidatoSignIn;
  fieldTextType!: boolean;
  token: string | undefined;

  registrationForm: FormGroup = new FormGroup({
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', [Validators.required]),
  });

  constructor(
    private rutaActiva: ActivatedRoute,
    private router: Router,
    private usuarioService: ServicioUsuariosService
  ) {}

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

  ingresar() {
    this.usuario = {
      correo: this.registrationForm.get('email')?.value,
      contraseña: this.registrationForm.get('password')?.value,
    };

    this.usuarioService.candidatoSignIn(this.usuario).subscribe(
      (data) => {
        if (data.message == 'Usuario no existe') {
          Swal.fire('', data.message, 'error');
        } else {
          Swal.fire('', data.message, 'success');
          // Manejar la respuesta exitosa aquí
          this.token = data.token;
          if (this.parametros.tipoUsuario == 'empresas') {
            this.router.navigate([`/menu/empresa`]);
          } else if (this.parametros.tipoUsuario == 'candidato') {
            this.router.navigate([`/menu/candidato`]);
          } else {
            this.router.navigate([`/menu/administrador`]);
          }
        }
      },
      (error: HttpErrorResponse) => {
        if (error.statusText == 'UNAUTHORIZED') {
          Swal.fire('', 'Contraseña incorrecta ', 'error');
        } else {
          Swal.fire(
            '',
            'Error en el ingreso con codigo ' + error.status,
            'error'
          );
        }
      }
    );
  }
}
