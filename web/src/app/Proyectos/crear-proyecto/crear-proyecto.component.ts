import { ServicioProyectosService } from './../Servicio/servicio-proyectos.service';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NgbCalendar, NgbDateStruct } from '@ng-bootstrap/ng-bootstrap';
import { proyectoCrear } from '../proyectos';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-crear-proyecto',
  templateUrl: './crear-proyecto.component.html',
  styleUrls: ['./crear-proyecto.component.css'],
})
export class CrearProyectoComponent implements OnInit {
  fechaInicioSeleccionada!: NgbDateStruct;
  fechaFinSeleccionada!: NgbDateStruct;

  proyecto!: proyectoCrear;
  chips: string[] = [];
  nuevoChip: string = '';

  registrationForm: FormGroup = new FormGroup({
    nombreProyecto: new FormControl('', [Validators.required]),
    descripcion: new FormControl('', [Validators.required]),
    perfil: new FormControl('', [Validators.required]),
    conocimientos: new FormControl('', [Validators.required]),
    fechaInicio: new FormControl('', [Validators.required]),
    fechaFin: new FormControl('', [Validators.required]),
  });

  constructor(
    private calendar: NgbCalendar,
    private router: Router,
    private proyectoService: ServicioProyectosService
  ) {}

  ngOnInit(): void {}

  abrirDatepicker() {
    // Inicializa la fecha con el valor actual
    this.fechaInicioSeleccionada = this.calendar.getToday();
  }

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

  verProyectos() {
    this.router.navigate([`/proyectos`]);
  }

  guardar() {
    this.proyecto = {
      idEmpresa: parseInt(sessionStorage.getItem('idEmpresa')!),
      nombre: this.registrationForm.get('nombreProyecto')?.value,
      descripcion: this.registrationForm.get('descripcion')?.value,
      perfiles: this.registrationForm.get('perfil')?.value,
      conocimientos_tecnicos: this.registrationForm.get('conocimientos')?.value,
      habilidades_blandas: this.chips.join(', '),
      startDate: new Date(
        this.fechaInicioSeleccionada.year,
        this.fechaInicioSeleccionada.month - 1,
        this.fechaInicioSeleccionada.day
      ),
      finishDate: new Date(
        this.fechaFinSeleccionada.year,
        this.fechaFinSeleccionada.month - 1,
        this.fechaFinSeleccionada.day
      ),
    };

    this.proyectoService.crearProyectos(this.proyecto).subscribe((res) => {
      //
      console.log(res);
      if (res.id > 0) {
        Swal.fire('', 'Proyecto creado', 'success');
        this.router.navigate([`/proyectos`]);
      } else {
        Swal.fire('', 'Error en la creación del proyecto', 'error');
      }
    });
  }
}
