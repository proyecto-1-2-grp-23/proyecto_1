import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';
import { candidatoRegistroPersonal } from '../candidato';
import { ServicioCandidatosService } from '../servicio/servicio-candidatos.service';

@Component({
  selector: 'app-login-candidato',
  templateUrl: './login-candidato.component.html',
  styleUrls: ['./login-candidato.component.css'],
})
export class LoginCandidatoComponent implements OnInit {
  fieldTextType!: boolean;
  repeatFieldTextType!: boolean;
  candidato!: candidatoRegistroPersonal;

  chips: string[] = [];
  nuevoChip: string = '';

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

  constructor(
    private router: Router,
    private candidatoService: ServicioCandidatosService
  ) {}

  ngOnInit(): void {}

  agregarChip() {
    console.log(this.nuevoChip, 'enter');
    if (this.nuevoChip.trim() !== '') {
      this.chips.push(this.nuevoChip);
      this.nuevoChip = ''; // Limpia el input
    }
  }

  eliminarChip(index: number) {
    this.chips.splice(index, 1);
  }

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
    if (
      this.registrationForm.get('repContraseña')?.value ==
      this.registrationForm.get('contraseña')?.value
    ) {
      this.candidato = {
        nombreCompleto: this.registrationForm.get('nombre')?.value,
        edad: this.registrationForm.get('edad')?.value,
        telefono: this.registrationForm.get('telefono')?.value.toString(),
        correo: this.registrationForm.get('correo')?.value,
        pais: this.registrationForm.get('pais')?.value,
        ciudad: this.registrationForm.get('ciudad')?.value,
        idiomas: this.registrationForm.get('idiomas')?.value,
        rasgosPersonalidad: this.chips.join(', '),
        password: this.registrationForm.get('contraseña')?.value,
      };

      this.candidatoService
        .candidatoRegistroPersonal(this.candidato)
        .subscribe((res) => {
          console.log(res);
          if (res.id > 0) {
            Swal.fire('', 'Candidato registrado', 'success');
            this.router.navigate([`/signin/candidato`]);
          } else {
            Swal.fire('', 'Error en el registro', 'error');
          }
        });
    } else {
      Swal.fire('', 'Las contraseñas no coinciden', 'error');
    }
  }
}
