import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ItemService } from '../../services/item.service';
import { Item } from '../../models/item.model';

@Component({
  selector: 'app-item-form',
  templateUrl: './item-form.component.html',
  styleUrls: ['./item-form.component.css']
})
export class ItemFormComponent {
  item: Item = {
    name: '',
    description: ''
  };

  constructor(
    private itemService: ItemService,
    private router: Router
  ) {}

  onSubmit(): void {
    this.itemService.createItem(this.item)
      .subscribe(() => {
        this.router.navigate(['/items']);
      });
  }
} 