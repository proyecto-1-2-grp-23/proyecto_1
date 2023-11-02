import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';

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
  constructor(private router: Router) {}

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

  resultado(element: any) {}

  detalle(element: any) {}
}
