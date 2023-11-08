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
    habilidades: new FormControl('', [Validators.required])
  });

  constructor(private calendar: NgbCalendar,
    private router: Router,
    private laboralService: ServicioRegistroLaboralService) { }

  ngOnInit(): void { }

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
      fecha_inicio: formatDate(this.registrationForm.get('fechaInicio')?.value),
      fecha_fin: formatDate(this.registrationForm.get('fechaFin')?.value),
      habilidades: this.registrationForm.get('habilidades')?.value,
    };
    function formatDate(date: { year: any; month: any; day: any; }) {
      const year = date.year;
      const month = String(date.month).padStart(2, '0');
      const day = String(date.day).padStart(2, '0');
      return `${year}-${month}-${day}`;
    }
    console.log(this.laboral)
    this.laboralService.candidatoRegistroLaboral(this.laboral)
      .subscribe((res) => {
        if (res.id > 0) {
          Swal.fire('', 'Candidato registrado', 'success');
          this.router.navigate([`/signin/candidato`]);
        } else {
          Swal.fire('', 'Error en el registro', 'error');
        }
      });

  }

  //this.router.navigate([`/menu/candidato`]);

}
