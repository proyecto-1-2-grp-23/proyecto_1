import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VistaEquipoTrabajoComponent } from './vista-equipo-trabajo.component';

describe('VistaEquipoTrabajoComponent', () => {
  let component: VistaEquipoTrabajoComponent;
  let fixture: ComponentFixture<VistaEquipoTrabajoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ VistaEquipoTrabajoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(VistaEquipoTrabajoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
