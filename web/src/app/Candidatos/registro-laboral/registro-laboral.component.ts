import { Router } from '@angular/router';
import { FormGroup, Validators, FormControl } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { NgbCalendar, NgbDateStruct } from '@ng-bootstrap/ng-bootstrap';
import { ServicioRegistroLaboralService } from '../servicio/servicio-registro-laboral.service';
import { candidatoRegistroLaboral } from '../laboral';
import Swal from 'sweetalert2';
import { candidatoSignIn } from 'src/app/Usuarios/usuario';

@Component({
  selector: 'app-registro-laboral',
  templateUrl: './registro-laboral.component.html',
  styleUrls: ['./registro-laboral.component.css'],
})
export class RegistroLaboralComponent implements OnInit {
  fechaInicioSeleccionada!: NgbDateStruct;
  fechaFinSeleccionada!: NgbDateStruct;
  laboral!: candidatoRegistroLaboral;

  chips: string[] = [];
  nuevoChip: string = '';

  registrationForm: FormGroup = new FormGroup({
    nombreEmpresa: new FormControl('', [Validators.required]),
    rol: new FormControl('', [Validators.required]),
    funciones: new FormControl('', [Validators.required]),
    fechaInicio: new FormControl('', [Validators.required]),
    fechaFin: new FormControl('', [Validators.required]),
    habilidades: new FormControl('', [Validators.required]),
  });

  constructor(
    private calendar: NgbCalendar,
    private router: Router,
    private laboralService: ServicioRegistroLaboralService
  ) {}

  ngOnInit(): void {}

  abrirDatepicker() {
    // Inicializa la fecha con el valor actual
    this.fechaInicioSeleccionada = this.calendar.getToday();
  }

  agregarChip() {
    console.log(this.nuevoChip, 'enter');
    if (this.nuevoChip) {
      this.chips.push(this.nuevoChip);
      this.nuevoChip = ''; // Limpia el input
    }
  }

  eliminarChip(index: number) {
    this.chips.splice(index, 1);
  }

  verMenu() {
    this.router.navigate([`/menu/candidato`]);
  }

  guardar() {
    this.laboral = {
      nombre_empresa: this.registrationForm.get('nombreEmpresa')?.value,
      rol: this.registrationForm.get('rol')?.value,
      funciones: this.registrationForm.get('funciones')?.value.toString(),
      fecha_inicio: new Date(
        this.fechaInicioSeleccionada.year,
        this.fechaInicioSeleccionada.month - 1,
        this.fechaInicioSeleccionada.day
      ),
      fecha_fin: new Date(
        this.fechaFinSeleccionada.year,
        this.fechaFinSeleccionada.month - 1,
        this.fechaFinSeleccionada.day
      ),
      habilidades: this.chips.join(','),
      idUsuario: parseInt(sessionStorage.getItem('idCandidato')!),
    };

    console.log(this.laboral);
    this.laboralService
      .candidatoRegistroLaboral(this.laboral)
      .subscribe((res) => {
        if (res.id > 0) {
          Swal.fire('', 'Data Laboral Agregada', 'success');
          this.router.navigate([`/menu/candidato`]);
        } else {
          Swal.fire('', 'Error en el registro laboral', 'error');
        }
      });
  }

  //this.router.navigate([`/menu/candidato`]);
}
