import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { entrevistaCrear } from '../entrevista';
import { ServicioEntrevistasService } from '../servicio/servicio-entrevistas.service';
import Swal from 'sweetalert2';
import { ServicioEmpresaService } from 'src/app/Empresas/servicio/servicio-empresa.service';
import { ServicioCandidatosService } from 'src/app/Candidatos/servicio/servicio-candidatos.service';
import { NgbDateStruct } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-programar-entrevista',
  templateUrl: './programar-entrevista.component.html',
  styleUrls: ['./programar-entrevista.component.css'],
})
export class ProgramarEntrevistaComponent implements OnInit {
  fechaInicioSeleccionada: any;
  entrevista!: entrevistaCrear;
  empresas: {
    Id: any;
    Nombre: any;
  }[] = [];

  candidatos: {
    Id: any;
    Nombre: any;
  }[] = [];

  funcionarios: {
    Id: any;
    Correo: any;
  }[] = [];

  registrationForm: FormGroup = new FormGroup({
    funcionario: new FormControl('', [Validators.required]),
    empresa: new FormControl('', [Validators.required]),
    candidato: new FormControl('', [Validators.required]),
    fecha: new FormControl('', [Validators.required]),
    lugar: new FormControl('', [Validators.required]),
  });

  constructor(
    private router: Router,
    private entravistaService: ServicioEntrevistasService,
    private candidatoService: ServicioCandidatosService,
    private empresaService: ServicioEmpresaService
  ) {}

  ngOnInit(): void {
    this.llenarFuncionarios();
    this.llenarCandidatos();
    this.llenarEmpresas();
  }

  guardar() {
    const fechaFormateada: NgbDateStruct =
      this.registrationForm.get('fecha')?.value;
    this.entrevista = {
      idCandidato: this.registrationForm.get('candidato')?.value,
      idEmpresa: this.registrationForm.get('empresa')?.value,
      idFuncionario: 1, //this.registrationForm.get('funcionario')?.value,
      fecha: new Date(
        fechaFormateada.year,
        fechaFormateada.month - 1,
        fechaFormateada.day
      ),
      lugar: this.registrationForm.get('lugar')?.value,
    };

    this.entravistaService
      .crearEntrevistas(this.entrevista)
      .subscribe((res) => {
        console.log(res);
        if (res.id > 0) {
          Swal.fire('', 'Entrevista programada', 'success');
          this.router.navigate([`/entrevistas`]);
        } else {
          Swal.fire('', 'Error en la programación de la entrevista', 'error');
        }
      });
  }

  cancelar() {
    this.router.navigate([`/entrevistas`]);
  }

  llenarFuncionarios() {
    this.entravistaService.listarFuncionarios().subscribe((res) => {
      console.log(res);
      res.forEach((registro: any) => {
        const correo = registro.correo;
        const id = registro.id;
        const nuevoRegistro = {
          Id: id,
          Correo: correo,
        };
        this.funcionarios.push(nuevoRegistro);
      });
    });
  }

  llenarCandidatos() {
    this.candidatoService.obtenerCandidatos().subscribe((res) => {
      console.log(res);
      res.forEach((registro: any) => {
        const nombre = registro.nombreCompleto;
        const id = registro.id;
        const nuevoRegistro = {
          Id: id,
          Nombre: nombre,
        };
        this.candidatos.push(nuevoRegistro);
      });
    });
  }

  llenarEmpresas() {
    this.empresaService.listarEmpresas().subscribe((res) => {
      console.log(res);
      res.forEach((registro: any) => {
        const nombre = registro.razonSocial;
        const id = registro.id;
        const nuevoRegistro = {
          Id: id,
          Nombre: nombre,
        };
        this.empresas.push(nuevoRegistro);
      });
    });
  }
}
