import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent implements OnInit {
  constructor(
    private router: Router,
    private translateService: TranslateService
  ) {}

  supportedLanguages = ['en', 'es'];

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

  seleccionarLenguaje(event: any) {
    this.translateService.use(event);
  }
}
