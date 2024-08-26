import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PathDoesNotExistsComponent } from './path.does.not.exists.component';

describe('PathdoesnotexistsComponent', () => {
  let component: PathDoesNotExistsComponent;
  let fixture: ComponentFixture<PathDoesNotExistsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PathDoesNotExistsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PathDoesNotExistsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
