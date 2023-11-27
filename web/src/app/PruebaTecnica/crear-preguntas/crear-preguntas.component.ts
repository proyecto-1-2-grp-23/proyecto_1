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
import { Respuesta, pruebaCrear } from '../pruebas';
import { ServicioPruebasService } from '../Servicio/servicio-pruebas.service';

@Component({
  selector: 'app-crear-preguntas',
  templateUrl: './crear-preguntas.component.html',
  styleUrls: ['./crear-preguntas.component.css'],
})
export class CrearPreguntasComponent implements OnInit, AfterViewInit {

  @ViewChild('referenceDiv') referenceDiv: ElementRef | undefined;

  registrationForm: FormGroup = new FormGroup({
    proyectos: new FormControl('', [Validators.required]),
    nivel: new FormControl('', [Validators.required]),
    pregunta: new FormControl('', [Validators.required]),
  });

  checkboxForm!: FormGroup;
  referenceDivHeight: number = 0;

  respuestas: string[] = [];
  nuevoItem: string = '';

  preguntas: {
    Nombre: string;
  }[] = [];

  proyectos: {
    Nombre: any;
  }[] = [];

  niveles: {
    Nivel: any;
  }[] = [];

  constructor(
    private formBuilder: FormBuilder,
    private cdr: ChangeDetectorRef,
    private pruebasService: ServicioPruebasService
  ) {}

  ngOnInit(): void {
    this.proyectos = [
      { Nombre: '1' },
      { Nombre: '2' }
    ]
    this.niveles = [{ Nivel: '1' }, { Nivel: '2' }, { Nivel: '3' }];

    this.preguntas = [
      { Nombre: 'pregunta 1' },
      { Nombre: 'pregunta 3' },
      { Nombre: 'pregunta 2' },
      { Nombre: 'pregunta 1' },
      { Nombre: 'pregunta 3' },
      { Nombre: 'pregunta 2' },
      { Nombre: 'pregunta 1' },
      { Nombre: 'pregunta 3' },
      { Nombre: 'pregunta 2' },
    ];

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
    console.log(this.selectedString?.value, 'this.selectedString?.value');

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
      this.respuestas.push(this.nuevoItem);
      this.nuevoItem = ''; // Limpia el input
    }
  }

  eliminarRespuesta(index: number) {
    this.respuestas.splice(index, 1);
    this.selectedString!.setValue('');
  }

  guardar() {
  };

}
