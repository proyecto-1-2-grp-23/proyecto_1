import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent implements OnInit {
  constructor(private router: Router) {}

  ngOnInit(): void {}

  empresa() {
    console.log('empresas');
    this.router.navigate([`/signin/empresas`]);
  }

  candidato() {
    console.log('candidato');
    this.router.navigate([`/signin/candidato`]);
  }

  administrador() {
    console.log('administrador');
    this.router.navigate([`/signin/administrador`]);
  }
}
