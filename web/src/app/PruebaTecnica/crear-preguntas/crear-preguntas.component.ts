import {
  AfterViewInit,
  ChangeDetectorRef,
  Component,
  ElementRef,
  OnInit,
  ViewChild,
} from '@angular/core';
import {
  FormArray,
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { Router } from '@angular/router';
import { ServicioEmpresaService } from 'src/app/Empresas/servicio/servicio-empresa.service';
import { ServicioProyectosService } from 'src/app/Proyectos/Servicio/servicio-proyectos.service';
import { pruebaTecnicaCrear } from '../pruebaTecnica';
import { ServicioPruebaTecnicaService } from '../Servicio/servicioPruebaTecnica.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-crear-preguntas',
  templateUrl: './crear-preguntas.component.html',
  styleUrls: ['./crear-preguntas.component.css'],
})
export class CrearPreguntasComponent implements OnInit, AfterViewInit {

  @ViewChild('referenceDiv') referenceDiv: ElementRef | undefined;

  registrationForm: FormGroup = new FormGroup({
    empresa: new FormControl('', [Validators.required]),
    proyectos: new FormControl('', [Validators.required]),
    nivel: new FormControl('', [Validators.required]),
    pregunta: new FormControl('', [Validators.required]),
  });

  checkboxForm!: FormGroup;
  referenceDivHeight: number = 0;
  dataTableProyectos: any;

  prueba!: pruebaTecnicaCrear;

  respuestas: {
    descripcion: string;
    esCorrecta: boolean;
  }[] = [];

  nuevoItem: string = '';

  preguntas: {
    Nombre: string;
  }[] = [];

  empresas: {
    Id: any;
    Nombre: any;
  }[] = [];

  proyectos: {
    Id: any;
    Nombre: any;
  }[] = [];

  niveles: {
    Nivel: any;
  }[] = [];

  constructor(
    private formBuilder: FormBuilder,
    private cdr: ChangeDetectorRef,
    private router: Router,
    private proyectoService: ServicioProyectosService,
    private empresaService: ServicioEmpresaService,
    private preguntaTecnicaService: ServicioPruebaTecnicaService
  ) {}

  ngOnInit(): void {
    this.llenarEmpresas();
    this.listarPreguntas();

    this.niveles = [{ Nivel: '1' }, { Nivel: '2' }, { Nivel: '3' }];

    this.checkboxForm = this.formBuilder.group({
      selectedString: new FormControl(''),
    });
  }

  ngAfterViewInit() {
    this.observeDivSizeChanges();
  }

  onResize() {
    // Función a ejecutar cuando cambia el tamaño del div de referencia
    this.calculateVerticalLineHeight();
  }

  calculateVerticalLineHeight() {
    if (this.referenceDiv) {
      const referenceDivHeight = this.referenceDiv.nativeElement.clientHeight;
      this.referenceDivHeight = referenceDivHeight - 20;
      // Marca manualmente para que Angular detecte los cambios
      this.cdr.detectChanges();
    }
  }

  private observeDivSizeChanges() {
    const resizeObserver = new ResizeObserver(() => {
      this.calculateVerticalLineHeight();
    });

    resizeObserver.observe(this.referenceDiv!.nativeElement);
  }

  get selectedString() {
    return this.checkboxForm.get('selectedString');
  }

  toggleSelection(selectedItem: string) {
    if (this.selectedString?.value === selectedItem) {
      // Desseleccionar la opción actual si ya está seleccionada
      this.selectedString.setValue('');
    } else {
      // Seleccionar la nueva opción y deseleccionar las demás
      this.selectedString?.setValue(selectedItem);
    }
  }

  agregarPregunta() {
    if (this.nuevoItem.trim() !== '') {
      const respuesta = this.nuevoItem;
      const esCorrecta = false;
      const nuevoRegistro = {
        descripcion: respuesta,
        esCorrecta: esCorrecta,
      };
      this.respuestas.push(nuevoRegistro);
      this.nuevoItem = ''; // Limpia el input
    }
  }

  eliminarRespuesta(index: number) {
    this.respuestas.splice(index, 1);
    this.selectedString!.setValue('');
  }

  verMenu() {
    this.router.navigate([`/menu/administrador`]);
  }

  setRespuestaCorrecta() {
    const indiceActualizar = this.respuestas.findIndex(
      (respuesta) => respuesta.descripcion === this.selectedString!.value
    );
    if (indiceActualizar !== -1) {
      this.respuestas[indiceActualizar].esCorrecta = true;
    }
  }

  guardar() {
    this.setRespuestaCorrecta();
    this.prueba = {
      idProyecto: this.registrationForm.get('proyectos')?.value,
      dificultad: this.registrationForm.get('nivel')?.value,
      descripcion: this.registrationForm.get('pregunta')?.value,
      respuestas: this.respuestas,
    };

    this.preguntaTecnicaService.crearPreguntas(this.prueba).subscribe((res) => {
      //
      console.log(res);
      if (res.id > 0) {
        this.listarPreguntas();
        this.limpiarFormulario();
        Swal.fire('', 'Proyecto Modificado', 'success');
      } else {
        Swal.fire('', 'Error en la modificación del proyecto', 'error');
      }
    });
  }

  cargarProyectos() {
    this.proyectos = [];
    console.log('ento');
    this.proyectoService
      .listarProyectos(this.registrationForm.get('empresa')?.value)
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

  llenarEmpresas() {
    this.empresaService.listarEmpresas().subscribe((res) => {
      console.log(res);
      res.forEach((registro: any) => {
        const nombre = registro.razonSocial;
        const id = registro.idUsuario;
        const nuevoRegistro = {
          Id: id,
          Nombre: nombre,
        };
        this.empresas.push(nuevoRegistro);
      });
    });
  }

  listarPreguntas() {
    this.preguntas = [];
    this.preguntaTecnicaService.listarPreguntas().subscribe((res) => {
      console.log(res);
      res.forEach((registro: any) => {
        console.log(registro, 'registros');
        const nombre = registro.descripcion;
        const id = registro.id;
        const nuevoRegistro = {
          Id: id,
          Nombre: nombre,
        };
        this.preguntas.push(nuevoRegistro);
      });
    });
  }

  limpiarFormulario() {
    window.location.reload();
  }
}
