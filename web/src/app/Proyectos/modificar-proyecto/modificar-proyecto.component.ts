import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Component, OnInit, Inject } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { NgbCalendar, NgbDateStruct } from '@ng-bootstrap/ng-bootstrap';
import { proyectoCrear } from '../proyectos';
import { ServicioProyectosService } from '../Servicio/servicio-proyectos.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-modificar-proyecto',
  templateUrl: './modificar-proyecto.component.html',
  styleUrls: ['./modificar-proyecto.component.css'],
})
export class ModificarProyectoComponent implements OnInit {
  registrationForm: FormGroup = new FormGroup({
    nombreProyecto: new FormControl('', [Validators.required]),
    descripcion: new FormControl('', [Validators.required]),
    perfil: new FormControl('', [Validators.required]),
    conocimientos: new FormControl('', [Validators.required]),
    fechaInicio: new FormControl('', [Validators.required]),
    fechaFin: new FormControl('', [Validators.required]),
  });

  fechaInicioSeleccionada!: NgbDateStruct;
  fechaFinSeleccionada!: NgbDateStruct;

  proyecto!: proyectoCrear;
  chips: string[] = [];
  nuevoChip: string = '';
  idProyecto: any;

  constructor(
    @Inject(MAT_DIALOG_DATA) data: any,
    private calendar: NgbCalendar,
    private proyectoService: ServicioProyectosService,
    public dialogRef: MatDialogRef<ModificarProyectoComponent>
  ) {
    if (data != null) {
      let informacion = data.info;
      console.log(informacion, 'info');
      this.idProyecto = informacion.id;

      this.registrationForm.get('nombreProyecto')?.setValue(informacion.Nombre);
      this.registrationForm
        .get('descripcion')
        ?.setValue(informacion.Descripcion);
      this.registrationForm.get('perfil')?.setValue(informacion.Perfiles);
      this.registrationForm
        .get('conocimientos')
        ?.setValue(informacion.Conocimientos);

      this.convertirFechaStringANgbDateStruct(
        informacion.FechaInicio,
        informacion.FechaFin
      );

      this.chips = informacion.Habilidades.split(',');
    }
  }

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
    this.dialogRef.close();
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
        Swal.fire('', 'Proyecto Modificado', 'success');
        this.dialogRef.close();
      } else {
        Swal.fire('', 'Error en la modificación del proyecto', 'error');
      }
    });
  }

  convertirFechaStringANgbDateStruct(
    fechaInicioString: String,
    fechaFinString: String
  ) {
    // Dividir la cadena en año, mes y día
    const partesFechaIni = fechaInicioString.split('-');
    const partesFechaFin = fechaFinString.split('-');

    // Crear el objeto NgbDateStruct
    this.fechaInicioSeleccionada = {
      year: +partesFechaIni[0], // Convierte la cadena a número
      month: +partesFechaIni[1],
      day: +partesFechaIni[2],
    };

    this.fechaFinSeleccionada = {
      year: +partesFechaFin[0], // Convierte la cadena a número
      month: +partesFechaFin[1],
      day: +partesFechaFin[2],
    };
  }
}
