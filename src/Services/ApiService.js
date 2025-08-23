import React, { useState, useEffect, createContext, useContext, useReducer } from 'react';


const API_BASE = process.env.REACT_APP_API_URL || 'http://localhost:8080/api';

class ApiService {
  static getHeaders() {
    return {
      'Content-Type': 'application/json',
      'Accept': 'application/json'
    };
  }

  static async handleResponse(response) {
    if (!response.ok) {
      const error = await response.text();
      throw new Error(`HTTP ${response.status}: ${error}`);
    }
    return response.json();
  }

  static async getPizzas() {
    try {
      const response = await fetch(`${API_BASE}/pizzas`, {
        method: 'GET',
        headers: this.getHeaders()
      });
      return await this.handleResponse(response);
    } catch (error) {
      console.error('Erreur lors du chargement des pizzas:', error);
      // Retourner des données de démonstration en cas d'erreur
      return [
        {
          id: 1,
          nom: "Margherita",
          ingredients: ["Tomate", "Mozzarella", "Basilic"],
          prix: 12.50
        },
        {
          id: 2,
          nom: "Pepperoni",
          ingredients: ["Tomate", "Mozzarella", "Pepperoni"],
          prix: 14.00
        },
        {
          id: 3,
          nom: "Quattro Stagioni",
          ingredients: ["Tomate", "Mozzarella", "Jambon", "Champignons", "Artichauts", "Olives"],
          prix: 16.50
        },
        {
          id: 4,
          nom: "Végétarienne",
          ingredients: ["Tomate", "Mozzarella", "Poivrons", "Champignons", "Courgettes"],
          prix: 15.00
        },
        {
          id: 5,
          nom: "Calzone",
          ingredients: ["Tomate", "Mozzarella", "Jambon", "Ricotta"],
          prix: 13.50
        },
        {
          id: 6,
          nom: "Orientale",
          ingredients: ["Tomate", "Mozzarella", "Merguez", "Poivrons", "Oignons"],
          prix: 15.50
        }
      ];
    }
  }

  static async createOrder(orderData) {
    try {
      const response = await fetch(`${API_BASE}/commandes`, {
        method: 'POST',
        headers: this.getHeaders(),
        body: JSON.stringify(orderData)
      });
      return await this.handleResponse(response);
    } catch (error) {
      console.error('Erreur lors de la création de commande:', error);
      // Simulation de réponse en cas d'erreur
      return {
        id: Date.now(),
        ...orderData,
        statut: 'confirmée'
      };
    }
  }

  static async getOrderById(id) {
    try {
      const response = await fetch(`${API_BASE}/commandes/${id}`, {
        method: 'GET',
        headers: this.getHeaders()
      });
      return await this.handleResponse(response);
    } catch (error) {
      console.error('Erreur lors du chargement de la commande:', error);
      return null;
    }
  }
}

// Validation
const ValidationRules = {
  required: (value) => {
    return value && value.trim() !== '' ? null : 'Ce champ est obligatoire';
  },

  email: (value) => {
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!value) return 'Email obligatoire';
    return emailRegex.test(value) ? null : 'Format email invalide';
  },

  phone: (value) => {
    const phoneRegex = /^(?:(?:\+|00)33|0)\s*[1-9](?:[\s.-]*\d{2}){4}$/;
    if (!value) return 'Téléphone obligatoire';
    return phoneRegex.test(value.replace(/\s/g, '')) ? null : 'Format téléphone invalide (ex: 01 23 45 67 89)';
  },

  minLength: (min) => (value) => {
    if (!value) return 'Ce champ est obligatoire';
    return value.length >= min ? null : `Minimum ${min} caractères requis`;
  }
};

const validateOrderForm = (formData) => {
  const errors = {};

  const nameError = ValidationRules.required(formData.nom) || ValidationRules.minLength(2)(formData.nom);
  if (nameError) errors.nom = nameError;

  const emailError = ValidationRules.email(formData.email);
  if (emailError) errors.email = emailError;

  const phoneError = ValidationRules.phone(formData.telephone);
  if (phoneError) errors.telephone = phoneError;

  const addressError = ValidationRules.required(formData.adresse) || ValidationRules.minLength(10)(formData.adresse);
  if (addressError) errors.adresse = addressError;

  return {
    isValid: Object.keys(errors).length === 0,
    errors
  };
};

const formatPhone = (phone) => {
  if (!phone) return '';
  const cleaned = phone.replace(/\D/g, '');
  const match = cleaned.match(/^(\d{2})(\d{2})(\d{2})(\d{2})(\d{2})$/);
  return match ? `${match[1]} ${match[2]} ${match[3]} ${match[4]} ${match[5]}` : phone;
};

const formatPrice = (price) => {
  return new Intl.NumberFormat('fr-FR', {
    style: 'currency',
    currency: 'EUR'
  }).format(price);
};