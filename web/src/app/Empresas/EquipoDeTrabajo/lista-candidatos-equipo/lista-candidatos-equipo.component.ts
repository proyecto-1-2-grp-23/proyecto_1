import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { ServicioEmpresaService } from '../../servicio/servicio-empresa.service';

@Component({
  selector: 'app-lista-candidatos-equipo',
  templateUrl: './lista-candidatos-equipo.component.html',
  styleUrls: ['./lista-candidatos-equipo.component.css'],
})
export class ListaCandidatosEquipoComponent implements OnInit {
  idEquipo: any;

  candidatos: {
    Nombre: any;
  }[] = [];

  constructor(
    @Inject(MAT_DIALOG_DATA) data: any,
    private servicioEmpresa: ServicioEmpresaService,
    public dialogRef: MatDialogRef<ListaCandidatosEquipoComponent>
  ) {
    if (data != null) {
      let informacion = data.info;
      this.idEquipo = informacion.Equipo;
    }
  }

  ngOnInit(): void {
    this.consultarCandidatos();
  }

  consultarCandidatos() {
    this.servicioEmpresa
      .listarCandidatosEquipo(this.idEquipo)
      .subscribe((res) => {
        res.forEach((registro: any) => {
          const nombre = registro.candidato.nombreCompleto;
          const nuevoRegistro = {
            Nombre: nombre,
          };
          this.candidatos.push(nuevoRegistro);
        });
      });
  }

  cerrar() {
    this.dialogRef.close();
  }
}
