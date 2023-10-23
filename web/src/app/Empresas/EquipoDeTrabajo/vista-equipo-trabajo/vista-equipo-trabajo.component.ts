import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-vista-equipo-trabajo',
  templateUrl: './vista-equipo-trabajo.component.html',
  styleUrls: ['./vista-equipo-trabajo.component.css'],
})
export class VistaEquipoTrabajoComponent implements OnInit {
  constructor(private router: Router) {}

  ngOnInit(): void {}

  verMenu() {
    this.router.navigate([`/menu/empresa`]);
  }

  crear() {
    this.router.navigate([`/crearEquipoDeTrabajo`]);
  }
}
