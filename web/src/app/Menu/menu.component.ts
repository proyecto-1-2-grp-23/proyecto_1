import { Component, ElementRef, HostListener, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';

import { TranslateService } from '@ngx-translate/core';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css'],
})
export class MenuComponent implements OnInit {
  parametros: { tipoUsuario: string } | any;
  tipoEmpresa!: String;
  estaAbierto = false;

  constructor(
    private rutaActiva: ActivatedRoute,
    private translateService: TranslateService,
    private router: Router,
    private elementRef: ElementRef
  ) {}

  ngOnInit(): void {
    this.parametros = {
      usuario: this.rutaActiva.snapshot.params['tipoUsuario'],
    };

    this.rutaActiva.params.subscribe((params: Params) => {
      this.parametros.tipoUsuario = params['tipoUsuario'];
    });

    this.tipoEmpresa = this.parametros.tipoUsuario;
  }

  toggleDropdown() {
    this.estaAbierto = !this.estaAbierto;
  }

  @HostListener('document:click', ['$event'])
  onDocumentClick(event: Event): void {
    if (!this.elementRef.nativeElement.contains(event.target)) {
      this.estaAbierto = false;
    }
  }

  verRegistroLaboral() {
    this.router.navigate([`/registroLaboral`]);
  }

  verEquipoDeTrabajo() {
    this.router.navigate([`/equipoDeTrabajo`]);
  }

  verEntrevistas() {
    this.router.navigate([`/entrevistas`]);
  }

  verProyectos() {
    this.router.navigate([`/proyectos`]);
  }

  verPreguntasTecnicas() {
    this.router.navigate([`/pruebaTecnica/preguntas`]);
  }

  verPrueba() {
    this.router.navigate([`/pruebaTecnica`]);
  }

  verRegistrarResultadoPrueba() {
    this.router.navigate([`/pruebaTecnica/registrarResultado`]);
  }

  verResultadoPrueba() {
    this.router.navigate([`/pruebaTecnica/verResultado`]);
  }

  verPruebaDesempeno() {
    this.router.navigate([`/pruebaDesempeño`]);
  }

  salir() {
    this.router.navigate([`/home`]);
    sessionStorage.removeItem('idEmpresa');
    sessionStorage.removeItem('idCandidato');
  }

  seleccionarLenguaje(event: any) {
    this.translateService.use(event);
  }
}
