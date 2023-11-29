import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ServicioProyectosService } from 'src/app/Proyectos/Servicio/servicio-proyectos.service';
import { ServicioPruebaTecnicaService } from '../Servicio/servicioPruebaTecnica.service';
import { ServicioEmpresaService } from 'src/app/Empresas/servicio/servicio-empresa.service';

@Component({
  selector: 'app-ver-resultado',
  templateUrl: './ver-resultado.component.html',
  styleUrls: ['./ver-resultado.component.css'],
})
export class VerResultadoComponent implements OnInit {
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

  constructor(
    private router: Router,
    private proyectoService: ServicioProyectosService,
    private preguntaTecnicaService: ServicioPruebaTecnicaService
  ) {}

  ngOnInit(): void {
    this.cargarProyectos();
  }

  verMenu() {
    this.router.navigate([`/menu/administrador`]);
  }

  cargarDatosPRoyecto() {
    this.consultarCandidatos();
  }

  consultarCandidatos() {
    this.proyectoService
      .listarCandidatosProyecto(this.registrationForm.get('proyectos')?.value)
      .subscribe((res) => {
        res.forEach((registro: any) => {
          if (registro.candidato[0]) {
            console.log(registro, 'regisro');
            const nombre = registro.candidato[0].nombreCompleto;
            const id = registro.candidato[0].idUsuario;
            const nuevoRegistro = {
              Nombre: nombre,
              Id: id,
            };
            this.candidatos.push(nuevoRegistro);
          }
        });
      });
  }
  cargarProyectos() {
    this.proyectos = [];
    console.log('ento');
    this.proyectoService.listarProyectosTodos().subscribe((res) => {
      res.forEach((registro: any) => {
        const id = registro.id;
        const nombre = registro.nombre;
        const nuevoRegistro = {
          Id: id,
          Nombre: nombre,
        };
        this.proyectos.push(nuevoRegistro);
      });
    });
  }

  llenarDatosPrueba() {
    let IdProyecto = this.registrationForm.get('proyectos')?.value;
    let idCandidato = this.registrationForm.get('candidatos')?.value;

    this.preguntaTecnicaService
      .consultarResultado(IdProyecto, idCandidato)
      .subscribe((res) => {
        this.registrationForm
          .get('pregunta1')
          ?.setValue(res.respuestas_enviadas[0].pregunta.descripcion);
        this.registrationForm
          .get('pregunta2')
          ?.setValue(res.respuestas_enviadas[1].pregunta.descripcion);
        this.registrationForm
          .get('pregunta3')
          ?.setValue(res.respuestas_enviadas[2].pregunta.descripcion);
        this.registrationForm
          .get('pregunta4')
          ?.setValue(res.respuestas_enviadas[3].pregunta.descripcion);
        this.registrationForm
          .get('pregunta5')
          ?.setValue(res.respuestas_enviadas[4].pregunta.descripcion);

        let rta1 = res.respuestas_enviadas[0].correcta
          ? 'Correcta'
          : 'Incorrecta';

        this.registrationForm.get('respuesta1')?.setValue(rta1);

        let rta2 = res.respuestas_enviadas[1].correcta
          ? 'Correcta'
          : 'Incorrecta';

        this.registrationForm.get('respuesta2')?.setValue(rta2);

        let rta3 = res.respuestas_enviadas[2].correcta
          ? 'Correcta'
          : 'Incorrecta';

        this.registrationForm.get('respuesta3')?.setValue(rta3);

        let rta4 = res.respuestas_enviadas[3].correcta
          ? 'Correcta'
          : 'Incorrecta';

        this.registrationForm.get('respuesta4')?.setValue(rta4);

        let rta5 = res.respuestas_enviadas[4].correcta
          ? 'Correcta'
          : 'Incorrecta';

        this.registrationForm.get('respuesta5')?.setValue(rta5);

        this.registrationForm.get('observaciones')?.setValue(res.observaciones);
      });
  }
}
