import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CrearEquipoTrabajoComponent } from './crear-equipo-trabajo.component';

describe('CrearEquipoTrabajoComponent', () => {
  let component: CrearEquipoTrabajoComponent;
  let fixture: ComponentFixture<CrearEquipoTrabajoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CrearEquipoTrabajoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CrearEquipoTrabajoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
