import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { ServicioPruebaTecnicaService } from '../Servicio/servicioPruebaTecnica.service';
import { ServicioProyectosService } from 'src/app/Proyectos/Servicio/servicio-proyectos.service';
import Swal from 'sweetalert2';
import { Router } from '@angular/router';
import { envioCrear } from '../envio';

@Component({
  selector: 'app-presentar-prueba',
  templateUrl: './presentar-prueba.component.html',
  styleUrls: ['./presentar-prueba.component.css'],
})
export class PresentarPruebaComponent implements OnInit {
  registrationForm!: FormGroup;
  tieneProyectos = false;
  proyectoSeleccionado = 0;
  numeroPregunta = 1;
  dificultadActual = 1;
  labelBoton = 'Siguiente';

  envio!: envioCrear;

  dataPreguntas: {
    descripcion: string;
    dificultad: number;
    respuestas: {
      descripcion: string;
      esCorrecta: boolean;
    };
    presentada: boolean;
  }[] = [];

  proyectos: {
    Id: number;
    Nombre: string;
  }[] = [];

  respuestas: {
    Id: number;
    Nombre: string;
    Correcta: boolean;
    Respuesta: boolean;
  }[] = [];

  respuestasAEnviar: {
    idRespuesta: number;
    correcta: boolean;
  }[] = [];

  constructor(
    private formBuilder: FormBuilder,
    private preguntaTecnicaService: ServicioPruebaTecnicaService,
    private proyectoService: ServicioProyectosService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.registrationForm = this.formBuilder.group({
      proyectos: new FormControl(''),
      selectedString: new FormControl(''),
      pregunta: new FormControl(''),
    });

    this.verificarProyectoEpleado();
  }

  get selectedString() {
    return this.registrationForm.get('selectedString');
  }

  toggleSelection(selectedItem: string) {
    console.log(this.selectedString?.value, 'this.selectedString?.value');

    if (this.selectedString?.value === selectedItem) {
      // Desseleccionar la opción actual si ya está seleccionada
      this.selectedString.setValue('');
    } else {
      // Seleccionar la nueva opción y deseleccionar las demás
      this.selectedString?.setValue(selectedItem);
    }
  }

  verificarProyectoEpleado() {
    this.proyectoService
      .listarProyectosDeCandidato(
        parseInt(sessionStorage.getItem('idCandidato')!)
      )
      .subscribe((res) => {
        if (res.resultado == 0) {
          Swal.fire(
            '',
            'No tienes asignados proyectos, por favor contactar con la empresa para realizar el proceso',
            'error'
          );
        } else {
          this.tieneProyectos = true;
          for (let valor of res.resultado) {
            const nuevoRegistro = {
              Id: valor.idProyecto,
              Nombre: valor.proyecto.nombre,
            };
            this.proyectos.push(nuevoRegistro);
          }
        }
      });
  }

  onClickProyecto() {
    this.proyectoSeleccionado = this.registrationForm.get('proyectos')?.value;
    Swal.fire(
      'Instrucciones',
      'Se presentarán 5 preguntas con diferentes dificultades para ser respondidas segun las opciones presentadas, al llegar a la pregunta 5 se realizará el envío de la pregunta y será redirigido al menú principal',
      'info'
    );
    this.cargarPreguntas();
  }

  cargarPreguntas() {
    this.preguntaTecnicaService
      .listarPreguntasProyecto(this.proyectoSeleccionado)
      .subscribe((res) => {
        console.log(res);
        res.forEach((registro: any) => {
          const descripcion = registro.descripcion;
          const dificultad = registro.dificultad;
          const respuestas = registro.respuestas;
          const nuevoRegistro = {
            descripcion: descripcion,
            dificultad: dificultad,
            respuestas: respuestas,
            presentada: false,
          };
          this.dataPreguntas.push(nuevoRegistro);
        });
        console.log(this.dataPreguntas, 'dataPreguntas');
        this.presentarPregunta();
      });
  }

  presentarPregunta() {
    let preguntasEncontradas: string | any[] = [];
    let indiceAleatorio = 0;

    if (this.dificultadActual == 1) {
      preguntasEncontradas = this.dataPreguntas.filter(
        (pregunta) => pregunta.dificultad == 1 && pregunta.presentada == false
      );
    }

    if (this.dificultadActual == 2) {
      preguntasEncontradas = this.dataPreguntas.filter(
        (pregunta) => pregunta.dificultad == 2 && pregunta.presentada == false
      );
    }

    if (this.dificultadActual == 3) {
      preguntasEncontradas = this.dataPreguntas.filter(
        (pregunta) => pregunta.dificultad == 3 && pregunta.presentada == false
      );
    }

    if (preguntasEncontradas.length > 0) {
      indiceAleatorio = Math.floor(Math.random() * preguntasEncontradas.length);
      this.registrationForm
        .get('pregunta')
        ?.setValue(preguntasEncontradas[indiceAleatorio].descripcion);

      preguntasEncontradas[indiceAleatorio].respuestas.forEach(
        (registro: any) => {
          const nuevoRegistro = {
            Id: registro.id,
            Nombre: registro.descripcion,
            Correcta: registro.esCorrecta,
            Respuesta: false,
          };
          this.respuestas.push(nuevoRegistro);
        }
      );
      this.marcarPreguntaPresentada();
    } else {
      Swal.fire(
        'Banco de preguntas insuficiente',
        'Por favor contactar al administrador',
        'error'
      );
      this.router.navigate([`/menu/candidato`]);
    }
  }

  aumentarDificultad() {
    if (this.dificultadActual == 3) {
      this.dificultadActual = 3;
    } else {
      this.dificultadActual += 1;
    }
  }

  disminuirDificultad() {
    if (this.dificultadActual == 1) {
      this.dificultadActual == 1;
    } else {
      this.dificultadActual -= 1;
    }
  }

  marcarPreguntaPresentada() {
    const indice = this.dataPreguntas.findIndex(
      (pregunta) =>
        pregunta.descripcion === this.registrationForm.get('pregunta')?.value
    );

    this.dataPreguntas[indice].presentada = true;
  }

  siguiente() {
    const indice = this.respuestas.findIndex(
      (respuesta) => respuesta.Nombre === this.selectedString?.value
    );

    let nuevoRegistro;

    this.respuestas[indice].Respuesta = true;

    if (this.respuestas[indice].Respuesta == this.respuestas[indice].Correcta) {
      Swal.fire('', 'Rspuesta correcta', 'success');
      nuevoRegistro = {
        idRespuesta: this.respuestas[indice].Id,
        correcta: true,
      };

      this.aumentarDificultad();
    } else {
      Swal.fire('', 'Rspuesta incorrecta', 'error');
      nuevoRegistro = {
        idRespuesta: this.respuestas[indice].Id,
        correcta: false,
      };
      this.disminuirDificultad();
    }

    this.respuestasAEnviar.push(nuevoRegistro);

    this.respuestas = [];
    this.numeroPregunta += 1;

    if (this.numeroPregunta <= 5) {
      this.presentarPregunta();
    }

    if (this.numeroPregunta == 5) {
      this.labelBoton = 'Finalizar';
    }

    if (this.numeroPregunta > 5) {
      Swal.fire('', 'Prueba finalizada', 'info');
      this.guardarRespuesta();
      this.router.navigate([`/menu/candidato`]);
    }
  }

  guardarRespuesta() {
    this.envio = {
      idProyecto: this.proyectoSeleccionado,
      idCandidato: parseInt(sessionStorage.getItem('idCandidato')!),
      observaciones: 'Prueba presentada en el sistema',
      respuestasEnviadas: this.respuestasAEnviar,
    };

    this.preguntaTecnicaService.crearEnvio(this.envio).subscribe((res) => {
      //
      console.log(res, 'rta envio');
    });
  }
}
