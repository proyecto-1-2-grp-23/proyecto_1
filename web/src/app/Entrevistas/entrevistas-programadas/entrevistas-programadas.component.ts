import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { DetalleEntrevistaComponent } from '../detalle-entrevista/detalle-entrevista.component';
import { MatDialog } from '@angular/material/dialog';
import { ResultadoEntrevistaComponent } from '../resultado-entrevista/resultado-entrevista.component';

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

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  constructor(private router: Router, private dialog: MatDialog) {}

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
    this.dataTableEntrevistas = [
      {
        Entrevista: '1',
        Empresa: 'Prueba',
        Candidato: 'Candidato2',
      },
      {
        Entrevista: '2',
        Empresa: 'Prueba2',
        Candidato: 'Candidato5',
      },
    ];

    this.dataSource = new MatTableDataSource(this.dataTableEntrevistas);
    this.dataSource.paginator = this.paginator;
  }

  programarEntrevista() {
    this.router.navigate([`/entrevistas/programarEntrevista`]);
  }

  verMenu() {
    this.router.navigate([`/menu/administrador`]);
  }

  resultado(element: any) {
    const dialogRef = this.dialog.open(ResultadoEntrevistaComponent, {
      width: '60%',
      height: '90%',
      data: { info: element },
    });
    dialogRef.afterClosed().subscribe((result) => {
      this.cargarEntrevistas();
    });
  }

  detalle(element: any) {
    const dialogRef = this.dialog.open(DetalleEntrevistaComponent, {
      width: '60%',
      height: '90%',
      data: { info: element },
    });
    dialogRef.afterClosed().subscribe((result) => {
      this.cargarEntrevistas();
    });
  }
}
