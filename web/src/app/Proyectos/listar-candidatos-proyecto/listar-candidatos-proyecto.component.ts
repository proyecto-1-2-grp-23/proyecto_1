import { Component, OnInit, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { ServicioProyectosService } from '../Servicio/servicio-proyectos.service';

@Component({
  selector: 'app-listar-candidatos-proyecto',
  templateUrl: './listar-candidatos-proyecto.component.html',
  styleUrls: ['./listar-candidatos-proyecto.component.css'],
})
export class ListarCandidatosProyectoComponent implements OnInit {
  idProyecto: any;

  candidatos: {
    Nombre: any;
  }[] = [];

  constructor(
    @Inject(MAT_DIALOG_DATA) data: any,
    private servicioProyecto: ServicioProyectosService,
    public dialogRef: MatDialogRef<ListarCandidatosProyectoComponent>
  ) {
    if (data != null) {
      this.idProyecto = data.Id;
    }
  }

  ngOnInit(): void {
    this.consultarCandidatos();
  }

  consultarCandidatos() {
    this.servicioProyecto
      .listarCandidatosProyecto(this.idProyecto)
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
