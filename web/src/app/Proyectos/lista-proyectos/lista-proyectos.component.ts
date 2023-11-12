import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { AgregarCandidatoProyectoComponent } from '../agregar-candidato-proyecto/agregar-candidato-proyecto.component';
import { ServicioProyectosService } from '../Servicio/servicio-proyectos.service';

@Component({
  selector: 'app-lista-proyectos',
  templateUrl: './lista-proyectos.component.html',
  styleUrls: ['./lista-proyectos.component.css'],
})
export class ListaProyectosComponent implements OnInit {
  dataTableProyectos: any;
  dataSource = new MatTableDataSource();
  columnas: any;
  displayedColumns: string[] | undefined;
  registros: {
    id: any;
    Nombre: any;
    Descripcion: any;
    Perfiles: any;
    Conocimientos: any;
    Habilidades: any;
    FechaInicio: any;
    FechaFin: any;
  }[] = [];

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  constructor(
    private router: Router,
    private dialog: MatDialog,
    private proyectoService: ServicioProyectosService
  ) {}

  ngOnInit(): void {
    this.dataSource.paginator = this.paginator;
    this.cargarColumnas();
    this.cargarProyectos();
  }

  cargarColumnas() {
    this.columnas = [{ nombre: 'Nombre' }, { nombre: 'Descripcion' }];

    this.displayedColumns = this.columnas.map((ele: any) => ele.nombre);
    this.displayedColumns = this.displayedColumns!.concat(['actions']);
  }

  cargarProyectos() {
    this.proyectoService.listarProyectos().subscribe((res) => {
      res.forEach((registro: any) => {
        const id = registro.id;
        const nombre = registro.nombre;
        const descripcion = registro.descripcion;
        const perfiles = registro.perfiles;
        const conocimientos = registro.conocimientos_tecnicos;
        const habilidades = registro.habilidades_blandas;
        const inicio = registro.startDate;
        const fin = registro.finishDate;
        const nuevoRegistro = {
          id: id,
          Nombre: nombre,
          Descripcion: descripcion,
          Perfiles: perfiles,
          Conocimientos: conocimientos,
          Habilidades: habilidades,
          FechaInicio: inicio,
          FechaFin: fin,
        };
        this.registros.push(nuevoRegistro);
      });

      this.dataTableProyectos = {};

      this.registros.forEach((item, index) => {
        this.dataTableProyectos[index] = item;
      });

      const miArreglo = Object.keys(this.dataTableProyectos).map(
        (key) => this.dataTableProyectos[key]
      );

      this.dataSource = new MatTableDataSource(miArreglo);
      this.dataSource.paginator = this.paginator;
    });
  }

  crearProyecto() {
    this.router.navigate([`/proyectos/crearProyecto`]);
  }

  verMenu() {
    this.router.navigate([`/menu/empresa`]);
  }

  agregarCandidatos(element: any) {
    localStorage.setItem('agregarCandidatosFunction', element.id);
    const dialogRef = this.dialog.open(AgregarCandidatoProyectoComponent, {
      width: '60%',
      height: '90%',
      data: { info: element },
    });
    dialogRef.afterClosed().subscribe((result) => {
      this.limpiarTabla();
      this.cargarProyectos();
    });
  }
  projectsbyId(){

    const projectId = this.dataTableProyectos[0].id
    this.proyectoService.listarProyectosById(projectId).subscribe((res) => {
      console.log(res[0]);
      console.log(res[0]);

    });
}

  limpiarTabla() {
    this.dataSource = new MatTableDataSource();
    this.registros = [];
  }
}
