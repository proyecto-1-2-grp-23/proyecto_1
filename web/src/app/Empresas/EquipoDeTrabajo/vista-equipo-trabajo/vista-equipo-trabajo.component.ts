import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { ServicioEmpresaService } from '../../servicio/servicio-empresa.service';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { AgregarCandidatoEquipoComponent } from '../agregar-candidato-equipo/agregar-candidato-equipo.component';

@Component({
  selector: 'app-vista-equipo-trabajo',
  templateUrl: './vista-equipo-trabajo.component.html',
  styleUrls: ['./vista-equipo-trabajo.component.css'],
})
export class VistaEquipoTrabajoComponent implements OnInit {
  dataTableEquipo: any;
  dataSource = new MatTableDataSource();
  columnas: any;
  displayedColumns: string[] | undefined;

  registros: {
    Equipo: any;
    Nombre: any;
    Descripcion: any;
  }[] = [];

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  constructor(
    private router: Router,
    private dialog: MatDialog,
    private empresaService: ServicioEmpresaService
  ) {}

  ngOnInit(): void {
    this.dataSource.paginator = this.paginator;
    this.cargarColumnas();
    this.cargarEquipos();
  }

  verMenu() {
    this.router.navigate([`/menu/empresa`]);
  }

  crear() {
    this.router.navigate([`/crearEquipoDeTrabajo`]);
  }

  agregarCandidato(element: any) {
    const dialogRef = this.dialog.open(AgregarCandidatoEquipoComponent, {
      width: '800px', // Puedes personalizar el tamaño
      height: '500px',
      data: { info: element },
    });

    dialogRef.afterClosed().subscribe((result) => {
      this.limpiarTabla();
      this.cargarEquipos();
    });
  }

  cargarColumnas() {
    this.columnas = [
      { nombre: 'Equipo' },
      { nombre: 'Nombre' },
      { nombre: 'Descripcion' },
    ];
    this.displayedColumns = this.columnas.map((ele: any) => ele.nombre);
    this.displayedColumns = this.displayedColumns!.concat(['actions']);
  }

  cargarEquipos() {
    this.empresaService.listarEquipo().subscribe((res) => {
      res.forEach((registro: any) => {
        const equipo = registro.id;
        const nombre = registro.nombre;
        const descripcion = registro.descripcion;
        const nuevoRegistro = {
          Equipo: equipo,
          Nombre: nombre,
          Descripcion: descripcion,
        };
        this.registros.push(nuevoRegistro);
      });

      this.dataTableEquipo = {};

      this.registros.forEach((item, index) => {
        this.dataTableEquipo[index] = item;
      });

      const miArreglo = Object.keys(this.dataTableEquipo).map(
        (key) => this.dataTableEquipo[key]
      );

      this.dataSource = new MatTableDataSource(miArreglo);
      this.dataSource.paginator = this.paginator;
    });
  }

  limpiarTabla() {
    this.dataSource = new MatTableDataSource();
    this.registros = [];
  }

  verCandidatos(element: any) {}
}
