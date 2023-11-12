import { equipoCrear } from './../equipo';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { AgregarCandidatoEquipoComponent } from '../agregar-candidato-equipo/agregar-candidato-equipo.component';
import { ServicioEmpresaService } from '../../servicio/servicio-empresa.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-crear-equipo-trabajo',
  templateUrl: './crear-equipo-trabajo.component.html',
  styleUrls: ['./crear-equipo-trabajo.component.css'],
})
export class CrearEquipoTrabajoComponent implements OnInit {
  registrationForm: FormGroup = new FormGroup({
    nombreGrupo: new FormControl('', [Validators.required]),
    descripcion: new FormControl('', [Validators.required]),
    funcionarios: new FormControl('', [Validators.required]),
  });

  equipo!: equipoCrear;

  funcionarios: {
    Id: any;
    Nombre: any;
  }[] = [];

  constructor(
    private router: Router,
    public dialog: MatDialog,
    private empresaService: ServicioEmpresaService
  ) {}

  ngOnInit(): void {
    this.llenarFuncionarios();
  }

  verLista() {
    this.router.navigate([`/equipoDeTrabajo`]);
  }

  guardar() {
    this.equipo = {
      idEmpresa: parseInt(sessionStorage.getItem('idEmpresa')!),
      nombre: this.registrationForm.get('nombreGrupo')?.value,
      descripcion: this.registrationForm.get('descripcion')?.value,
      idFuncionario: parseInt(this.registrationForm.get('funcionarios')?.value),
    };

    this.empresaService.crearEquipo(this.equipo).subscribe((res) => {
      console.log(res);
      if (res.id > 0) {
        Swal.fire('', 'Equipo creado', 'success');
        this.router.navigate([`/equipoDeTrabajo`]);
      } else {
        Swal.fire('', 'Error en la creación del equipo', 'error');
      }
    });
  }

  llenarFuncionarios() {
    this.empresaService
      .listarFuncionarios(parseInt(sessionStorage.getItem('idEmpresa')!))
      .subscribe((res) => {
        console.log(res);
        res.forEach((registro: any) => {
          const nombre = registro.nombre_funcionario;
          const id = registro.id;
          const nuevoRegistro = {
            Id: id,
            Nombre: nombre,
          };
          this.funcionarios.push(nuevoRegistro);
        });
      });
  }
}
