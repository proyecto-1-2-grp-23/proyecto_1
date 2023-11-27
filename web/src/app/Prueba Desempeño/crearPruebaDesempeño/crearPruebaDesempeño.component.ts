import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ServicioCandidatosService } from 'src/app/Candidatos/servicio/servicio-candidatos.service';
import { pruebaDesempeñoCrear } from '../pruebaDesempeño';
import { ServicioPruebaDesempeñoService } from '../Servicio/servicioPruebaDesempeño.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-crearPruebaDesempeño',
  templateUrl: './crearPruebaDesempeño.component.html',
  styleUrls: ['./crearPruebaDesempeño.component.css'],
})
export class CrearPruebaDesempeñoComponent implements OnInit {
  registrationForm: FormGroup = new FormGroup({
    candidatos: new FormControl('', [Validators.required]),
  });

  candidatos: {
    Nombre: any;
    Id: any;
  }[] = [];

  habilidades: {
    Nombre: any;
    Puntaje: any;
  }[] = [];

  nuevoItem: string = '';
  nuevoPuntaje: string = '';

  prueba!: pruebaDesempeñoCrear;

  constructor(
    private candidatoService: ServicioCandidatosService,
    private pruebaDesenoService: ServicioPruebaDesempeñoService,
    private router: Router
  ) {}

  ngOnInit() {
    this.llenarCandidatos();
  }

  llenarCandidatos() {
    this.candidatoService.obtenerCandidatos().subscribe((res) => {
      console.log(res);
      res.forEach((registro: any) => {
        const nombre = registro.nombreCompleto;
        const id = registro.id;
        const nuevoRegistro = {
          Id: id,
          Nombre: nombre,
        };
        this.candidatos.push(nuevoRegistro);
      });
    });
  }

  eliminarRespuesta(index: number) {
    this.habilidades.splice(index, 1);
  }

  agregarPregunta() {
    if (this.nuevoItem.trim() !== '') {
      const Nombre = this.nuevoItem;
      const Puntaje = this.nuevoPuntaje;
      const nuevoRegistro = {
        Nombre: Nombre,
        Puntaje: Puntaje,
      };
      this.habilidades.push(nuevoRegistro);
      this.nuevoItem = '';
      this.nuevoPuntaje = ''; // Limpia el input
    }
  }

  verMenu() {
    this.router.navigate([`/menu/administrador`]);
  }

  guardar() {
    this.prueba = {
      idEmpresa: parseInt(sessionStorage.getItem('idEmpresa')!),
      idPrueba: 1,
      idCandidato: this.registrationForm.get('candidatos')?.value,
      habilidad: this.habilidades[0].Nombre,
      puntaje: this.habilidades[0].Puntaje,
    };

    this.pruebaDesenoService
      .crearPruebaDesempeno(this.prueba)
      .subscribe((res) => {
        //
        console.log(res);
        if (res.id > 0) {
          Swal.fire('', 'Prueba creada', 'success');
          this.verMenu();
        } else {
          Swal.fire('', 'Error en la creacion de la prueba', 'error');
        }
      });
  }
}
