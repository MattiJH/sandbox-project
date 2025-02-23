import { Component, OnInit } from '@angular/core';
import { Item } from '../../models/item.model';
import { ItemService } from '../../services/item.service';

@Component({
  selector: 'app-item-list',
  templateUrl: './item-list.component.html',
  styleUrls: ['./item-list.component.css']
})
export class ItemListComponent implements OnInit {
  items: Item[] = [];

  constructor(private itemService: ItemService) { }

  ngOnInit(): void {
    this.loadItems();
  }

  loadItems(): void {
    this.itemService.getItems()
      .subscribe(items => this.items = items);
  }

  deleteItem(id: number): void {
    this.itemService.deleteItem(id)
      .subscribe(() => {
        this.items = this.items.filter(item => item.id !== id);
      });
  }
} 