import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { empresaoRegistro } from '../empresa';
import Swal from 'sweetalert2';
import { ServicioEmpresaService } from '../servicio/servicio-empresa.service';

@Component({
  selector: 'app-login-empresa',
  templateUrl: './login-empresa.component.html',
  styleUrls: ['./login-empresa.component.css'],
})
export class LoginEmpresaComponent implements OnInit {
  fieldTextType!: boolean;
  repeatFieldTextType!: boolean;

  empresa!: empresaoRegistro;

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

  constructor(
    private router: Router,
    private empresaService: ServicioEmpresaService
  ) {}

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
    if (
      this.registrationForm.get('repContraseña')?.value ==
      this.registrationForm.get('contraseña')?.value
    ) {
      this.empresa = {
        razonSocial: this.registrationForm.get('razonSocial')?.value,
        tipoEmpresa: this.registrationForm.get('tipoEmpresa')?.value,
        correo: this.registrationForm.get('correo')?.value,
        pais: this.registrationForm.get('pais')?.value,
        ciudad: this.registrationForm.get('ciudad')?.value,
        verticalesNegocio: this.registrationForm.get('verticales')?.value,
        password: this.registrationForm.get('contraseña')?.value,
      };

      this.empresaService.empresaRegistro(this.empresa).subscribe((res) => {
        console.log(res);
        if (res.id > 0) {
          Swal.fire('', 'Empresa registrada', 'success');
          this.router.navigate([`/signin/empresas`]);
        } else {
          Swal.fire('', 'Error en el registro', 'error');
        }
      });
    } else {
      Swal.fire('', 'Las contraseñas no coinciden', 'error');
    }
  }
}
