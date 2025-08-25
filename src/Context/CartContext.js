import React, { createContext, useContext, useReducer, useEffect } from 'react';

// Actions du reducer
const CartActions = {
  ADD_TO_CART: 'ADD_TO_CART',
  REMOVE_FROM_CART: 'REMOVE_FROM_CART',
  UPDATE_QUANTITY: 'UPDATE_QUANTITY',
  CLEAR_CART: 'CLEAR_CART',
  LOAD_CART: 'LOAD_CART'
};

// Reducer pour gérer le state du panier
const cartReducer = (state, action) => {
  switch (action.type) {
    case CartActions.ADD_TO_CART: {
      const existingItemIndex = state.items.findIndex(
        item => item.id === action.payload.id
      );

      if (existingItemIndex >= 0) {
        const updatedItems = [...state.items];
        updatedItems[existingItemIndex].quantite += 1;
        return { ...state, items: updatedItems };
      } else {
        return {
          ...state,
          items: [...state.items, { ...action.payload, quantite: 1 }]
        };
      }
    }

    case CartActions.REMOVE_FROM_CART: {
      return {
        ...state,
        items: state.items.filter(item => item.id !== action.payload)
      };
    }

    case CartActions.UPDATE_QUANTITY: {
      if (action.payload.quantite <= 0) {
        return {
          ...state,
          items: state.items.filter(item => item.id !== action.payload.id)
        };
      }

      return {
        ...state,
        items: state.items.map(item =>
          item.id === action.payload.id
            ? { ...item, quantite: action.payload.quantite }
            : item
        )
      };
    }

    case CartActions.CLEAR_CART: {
      return { ...state, items: [] };
    }

    case CartActions.LOAD_CART: {
      return { ...state, items: action.payload || [] };
    }

    default:
      return state;
  }
};

// State initial
const initialState = {
  items: []
};

// Context
const CartContext = createContext();

// Hook pour utiliser le context
export const useCart = () => {
  const context = useContext(CartContext);
  if (!context) {
    throw new Error('useCart doit être utilisé dans CartProvider');
  }
  return context;
};

// Provider du context
export const CartProvider = ({ children }) => {
  const [state, dispatch] = useReducer(cartReducer, initialState);

  // Charger le panier depuis localStorage au démarrage
  useEffect(() => {
    const savedCart = localStorage.getItem('pizza_cart');
    if (savedCart) {
      try {
        const cartData = JSON.parse(savedCart);
        dispatch({ type: CartActions.LOAD_CART, payload: cartData });
      } catch (error) {
        console.error('Erreur chargement panier:', error);
      }
    }
  }, []);

  // Sauvegarder le panier dans localStorage à chaque modification
  useEffect(() => {
    localStorage.setItem('pizza_cart', JSON.stringify(state.items));
  }, [state.items]);

  // Actions
  const addToCart = (pizza) => {
    dispatch({ type: CartActions.ADD_TO_CART, payload: pizza });
  };

  const removeFromCart = (pizzaId) => {
    dispatch({ type: CartActions.REMOVE_FROM_CART, payload: pizzaId });
  };

  const updateQuantity = (pizzaId, quantite) => {
    dispatch({ 
      type: CartActions.UPDATE_QUANTITY, 
      payload: { id: pizzaId, quantite } 
    });
  };

  const clearCart = () => {
    dispatch({ type: CartActions.CLEAR_CART });
  };

  // Calculer le total
  const getTotalPrice = () => {
    return state.items.reduce((total, item) => {
      return total + (item.prix * item.quantite);
    }, 0);
  };

  // Calculer le nombre total d'articles
  const getTotalItems = () => {
    return state.items.reduce((total, item) => total + item.quantite, 0);
  };

  const value = {
    cartItems: state.items,
    addToCart,
    removeFromCart,
    updateQuantity,
    clearCart,
    getTotalPrice,
    getTotalItems
  };

  return (
    <CartContext.Provider value={value}>
      {children}
    </CartContext.Provider>
  );
};