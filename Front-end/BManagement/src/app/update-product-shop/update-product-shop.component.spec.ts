import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateProductShopComponent } from './update-product-shop.component';

describe('UpdateProductShopComponent', () => {
  let component: UpdateProductShopComponent;
  let fixture: ComponentFixture<UpdateProductShopComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UpdateProductShopComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdateProductShopComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
