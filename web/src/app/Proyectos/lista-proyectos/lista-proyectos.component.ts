import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { AgregarCandidatoProyectoComponent } from '../agregar-candidato-proyecto/agregar-candidato-proyecto.component';

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

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  constructor(private router: Router, private dialog: MatDialog) {}

  ngOnInit(): void {
    this.dataSource.paginator = this.paginator;
    this.cargarColumnas();
    this.cargarProyectos();
  }

  cargarColumnas() {
    this.columnas = [{ nombre: 'Proyecto' }, { nombre: 'Descripcion' }];

    this.displayedColumns = this.columnas.map((ele: any) => ele.nombre);

    this.displayedColumns = this.displayedColumns!.concat(['actions']);
  }

  cargarProyectos() {
    this.dataTableProyectos = [
      {
        Proyecto: '1',
        Descripcion: 'Prueba',
      },
      {
        Proyecto: '2',
        Descripcion: 'Prueba2',
      },
    ];

    this.dataSource = new MatTableDataSource(this.dataTableProyectos);
    this.dataSource.paginator = this.paginator;
  }

  crearProyecto() {
    this.router.navigate([`/proyectos/crearProyecto`]);
  }

  verMenu() {
    this.router.navigate([`/menu/empresa`]);
  }

  agregarCandidatos(element: any) {
    const dialogRef = this.dialog.open(AgregarCandidatoProyectoComponent, {
      width: '60%',
      height: '90%',
      data: { info: element },
    });
    dialogRef.afterClosed().subscribe((result) => {
      this.cargarProyectos();
    });
  }
}
