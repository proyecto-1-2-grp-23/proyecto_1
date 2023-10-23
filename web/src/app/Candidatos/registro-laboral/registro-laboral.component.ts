import { Router } from '@angular/router';
import { FormGroup, Validators, FormControl } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import {
  NgbCalendar,
  NgbDateStruct,
  NgbDate,
} from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-registro-laboral',
  templateUrl: './registro-laboral.component.html',
  styleUrls: ['./registro-laboral.component.css'],
})
export class RegistroLaboralComponent implements OnInit {
  fechaInicioSeleccionada!: NgbDateStruct;
  fechaFinSeleccionada!: NgbDateStruct;

  chips: string[] = [];
  nuevoChip: string = '';

  registrationForm: FormGroup = new FormGroup({
    nombreEmpresa: new FormControl('', [Validators.required]),
    rol: new FormControl('', [Validators.required]),
    funciones: new FormControl('', [Validators.required]),
    fechaInicio: new FormControl('', [Validators.required]),
    fechaFin: new FormControl('', [Validators.required]),
  });

  constructor(private calendar: NgbCalendar, private router: Router) {}

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
    this.router.navigate([`/menu/candidato`]);
  }
}
