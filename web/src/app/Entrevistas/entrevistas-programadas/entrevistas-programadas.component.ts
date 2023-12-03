import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { DetalleEntrevistaComponent } from '../detalle-entrevista/detalle-entrevista.component';
import { MatDialog } from '@angular/material/dialog';
import { ResultadoEntrevistaComponent } from '../resultado-entrevista/resultado-entrevista.component';
import { ServicioEntrevistasService } from '../servicio/servicio-entrevistas.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-entrevistas-programadas',
  templateUrl: './entrevistas-programadas.component.html',
  styleUrls: ['./entrevistas-programadas.component.css'],
})
export class EntrevistasProgramadasComponent implements OnInit {
  dataTableEntrevistas: any;
  dataSource = new MatTableDataSource();
  columnas: any;
  displayedColumns: string[] | undefined;
  registros: {
    Entrevista: any;
    Empresa: any;
    Candidato: any;
    Funcionario: any;
    Fecha: any;
    Lugar: any;
    Estado: any;
    Descripcion: any;
    Observaciones: any;
  }[] = [];

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  constructor(
    private router: Router,
    private dialog: MatDialog,
    private entravistaService: ServicioEntrevistasService
  ) {}

  ngOnInit(): void {
    this.dataSource.paginator = this.paginator;
    this.cargarColumnas();
    this.cargarEntrevistas();
  }

  cargarColumnas() {
    this.columnas = [
      { nombre: 'Entrevista' },
      { nombre: 'Empresa' },
      { nombre: 'Candidato' },
    ];

    this.displayedColumns = this.columnas.map((ele: any) => ele.nombre);
    this.displayedColumns = this.displayedColumns!.concat(['actions']);
  }

  cargarEntrevistas() {
    this.entravistaService.listarEntrevistas().subscribe((res) => {
      res.forEach((registro: any) => {
        const entrevista = registro.id;
        const empresa = registro.empresa.razonSocial;
        const candidato = registro.candidato.nombreCompleto;
        const funcionario = registro.funcionario.correo;
        const fecha = registro.fecha;
        const lugar = registro.lugar;
        const estado = registro.resultado;
        const descripcion =
          'Entrevista realizada para ' + registro.empresa.razonSocial;
        const observaciones = registro.observaciones;
        const nuevoRegistro = {
          Entrevista: entrevista,
          Empresa: empresa,
          Candidato: candidato,
          Funcionario: funcionario,
          Fecha: fecha,
          Lugar: lugar,
          Estado: estado,
          Descripcion: descripcion,
          Observaciones: observaciones,
        };
        this.registros.push(nuevoRegistro);
      });

      this.dataTableEntrevistas = {};

      this.registros.forEach((item, index) => {
        this.dataTableEntrevistas[index] = item;
      });

      const miArreglo = Object.keys(this.dataTableEntrevistas).map(
        (key) => this.dataTableEntrevistas[key]
      );

      this.dataSource = new MatTableDataSource(miArreglo);
      this.dataSource.paginator = this.paginator;
    });
  }

  programarEntrevista() {
    this.router.navigate([`/entrevistas/programarEntrevista`]);
  }

  verMenu() {
    this.router.navigate([`/menu/administrador`]);
  }

  resultado(element: any) {
    if (element.resultado == '') {
      Swal.fire('', 'No hay resultado registrado', 'info');
    } else {
      const dialogRef = this.dialog.open(ResultadoEntrevistaComponent, {
        width: '60%',
        height: '90%',
        data: { info: element },
      });
      dialogRef.afterClosed().subscribe((result) => {
        this.limpiarTabla();
        this.cargarEntrevistas();
      });
    }
  }

  detalle(element: any) {
    const dialogRef = this.dialog.open(DetalleEntrevistaComponent, {
      width: '60%',
      height: '90%',
      data: { info: element },
    });
    dialogRef.afterClosed().subscribe((result) => {
      this.limpiarTabla();
      this.cargarEntrevistas();
    });
  }

  limpiarTabla() {
    this.dataSource = new MatTableDataSource();
    this.registros = [];
  }
}
