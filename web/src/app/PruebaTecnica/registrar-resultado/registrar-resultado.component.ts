interface Respuesta {
  valor: string;
  esCorrecta: boolean;
}

import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ServicioProyectosService } from 'src/app/Proyectos/Servicio/servicio-proyectos.service';
import { ServicioPruebaTecnicaService } from '../Servicio/servicioPruebaTecnica.service';
import { envioCrear } from '../envio';
import Swal from 'sweetalert2';

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
    listRespuesta1: new FormControl('', [Validators.required]),
    listRespuesta2: new FormControl('', [Validators.required]),
    listRespuesta3: new FormControl('', [Validators.required]),
    listRespuesta4: new FormControl('', [Validators.required]),
    listRespuesta5: new FormControl('', [Validators.required]),
    respuesta1: new FormControl('', [Validators.required]),
    respuesta2: new FormControl('', [Validators.required]),
    respuesta3: new FormControl('', [Validators.required]),
    respuesta4: new FormControl('', [Validators.required]),
    respuesta5: new FormControl('', [Validators.required]),
    observaciones: new FormControl('', [Validators.required]),
  });

  envio!: envioCrear;

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

  respuestas: Respuesta[] = [
    {
      valor: 'Correcto',
      esCorrecta: true,
    },
    { valor: 'Incorrecto', esCorrecta: false },
  ];

  opciones1: {
    Nombre: any;
    Id: any;
  }[] = [];

  opciones2: {
    Nombre: any;
    Id: any;
  }[] = [];

  opciones3: {
    Nombre: any;
    Id: any;
  }[] = [];

  opciones4: {
    Nombre: any;
    Id: any;
  }[] = [];

  opciones5: {
    Nombre: any;
    Id: any;
  }[] = [];

  respuestasAEnviar: {
    idRespuesta: number;
    correcta: boolean;
  }[] = [];

  dataPreguntas: any;

  constructor(
    private router: Router,
    private proyectoService: ServicioProyectosService,
    private preguntaTecnicaService: ServicioPruebaTecnicaService
  ) {}

  ngOnInit(): void {
    this.cargarProyectos();
  }

  verMenu() {
    this.router.navigate([`/menu/empresa`]);
  }

  cargarProyectos() {
    this.proyectos = [];
    console.log('ento');
    this.proyectoService
      .listarProyectos(parseInt(sessionStorage.getItem('idEmpresa')!))
      .subscribe((res) => {
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

  cargarDatosPRoyecto() {
    this.consultarCandidatos();
    this.cargarPreguntas();
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

  cargarPreguntas() {
    this.preguntaTecnicaService
      .listarPreguntasProyecto(this.registrationForm.get('proyectos')?.value)
      .subscribe((res) => {
        console.log(res);
        this.dataPreguntas = res;
        res.forEach((registro: any) => {
          const descripcion = registro.descripcion;
          const id = registro.id;
          const nuevoRegistro = {
            Nombre: descripcion,
            Id: id,
          };
          this.preguntas.push(nuevoRegistro);
        });
      });
  }

  guardarRespuesta() {
    const nuevoRegistro1 = {
      idRespuesta: this.registrationForm.get('listRespuesta1')?.value,
      correcta: this.registrationForm.get('respuesta1')?.value,
    };
    this.respuestasAEnviar.push(nuevoRegistro1);

    const nuevoRegistro2 = {
      idRespuesta: this.registrationForm.get('listRespuesta2')?.value,
      correcta: this.registrationForm.get('respuesta2')?.value,
    };
    this.respuestasAEnviar.push(nuevoRegistro2);

    const nuevoRegistro3 = {
      idRespuesta: this.registrationForm.get('listRespuesta3')?.value,
      correcta: this.registrationForm.get('respuesta3')?.value,
    };
    this.respuestasAEnviar.push(nuevoRegistro3);

    const nuevoRegistro4 = {
      idRespuesta: this.registrationForm.get('listRespuesta4')?.value,
      correcta: this.registrationForm.get('respuesta4')?.value,
    };
    this.respuestasAEnviar.push(nuevoRegistro4);

    const nuevoRegistro5 = {
      idRespuesta: this.registrationForm.get('listRespuesta5')?.value,
      correcta: this.registrationForm.get('respuesta5')?.value,
    };
    this.respuestasAEnviar.push(nuevoRegistro5);

    console.log(this.respuestasAEnviar, 'respuestasAEnviar');

    this.envio = {
      idProyecto: this.registrationForm.get('proyectos')?.value,
      idCandidato: this.registrationForm.get('candidatos')?.value,
      observaciones: this.registrationForm.get('observaciones')?.value,
      respuestasEnviadas: this.respuestasAEnviar,
    };

    this.preguntaTecnicaService.crearEnvio(this.envio).subscribe((res) => {
      //
      if (res.id > 0) {
        Swal.fire('', 'Registro guardado', 'success');
        this.router.navigate([`/menu/empresa`]);
      } else {
        Swal.fire('', 'Error al guardar el registro', 'success');
      }
    });
  }

  llenarOpciones(opcion: number) {
    let preguntaId = 0;

    preguntaId = this.registrationForm.get(
      'pregunta' + opcion.toString()
    )?.value;

    console.log(preguntaId, 'preguntaId');

    let preguntaEncontrada = this.dataPreguntas.filter(
      (pregunta: { id: any }) => pregunta.id == preguntaId
    );

    console.log(preguntaEncontrada, 'preguntaEncontrada');

    preguntaEncontrada[0].respuestas.forEach(
      (rta: { descripcion: any; id: any }) => {
        const nuevoRegistro = {
          Nombre: rta.descripcion,
          Id: rta.id,
        };

        switch (opcion) {
          case 1:
            this.opciones1.push(nuevoRegistro);
            break;
          case 2:
            this.opciones2.push(nuevoRegistro);
            break;
          case 3:
            this.opciones3.push(nuevoRegistro);
            break;
          case 4:
            this.opciones4.push(nuevoRegistro);
            break;
          case 5:
            this.opciones5.push(nuevoRegistro);
            break;
        }
      }
    );
  }
}
