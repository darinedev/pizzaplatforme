import React from 'react';
import { formatPhone } from '../utils/validation';

const OrderForm = ({ formData, formErrors, onChange, onSubmit, loading }) => {
  
  const handleInputChange = (e) => {
    const { name, value } = e.target;
    
    // Formatage automatique du téléphone
    if (name === 'telephone') {
      const formattedPhone = formatPhone(value);
      onChange(name, formattedPhone);
    } else {
      onChange(name, value);
    }
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    onSubmit();
  };

  const InputField = ({ 
    name, 
    label, 
    type = 'text', 
    required = true, 
    placeholder, 
    rows 
  }) => (
    <div>
      <label className="block text-sm font-medium text-gray-700 mb-1">
        {label} {required && <span className="text-red-500">*</span>}
      </label>
      
      {type === 'textarea' ? (
        <textarea
          name={name}
          value={formData[name] || ''}
          onChange={handleInputChange}
          rows={rows || 3}
          placeholder={placeholder}
          className={`w-full px-3 py-2 border rounded-md focus:outline-none focus:ring-2 transition-colors resize-none ${
            formErrors[name]
              ? 'border-red-500 focus:ring-red-500'
              : 'border-gray-300 focus:ring-red-500'
          }`}
        />
      ) : (
        <input
          type={type}
          name={name}
          value={formData[name] || ''}
          onChange={handleInputChange}
          placeholder={placeholder}
          className={`w-full px-3 py-2 border rounded-md focus:outline-none focus:ring-2 transition-colors ${
            formErrors[name]
              ? 'border-red-500 focus:ring-red-500'
              : 'border-gray-300 focus:ring-red-500'
          }`}
        />
      )}
      
      {formErrors[name] && (
        <p className="mt-1 text-sm text-red-600 flex items-center">
          <span className="mr-1">⚠️</span>
          {formErrors[name]}
        </p>
      )}
    </div>
  );

  return (
    <div className="bg-white p-6 rounded-lg shadow-md">
      <h2 className="text-2xl font-bold text-gray-800 mb-6 flex items-center">
        <span className="mr-2">📋</span>
        Informations de livraison
      </h2>

      <div onSubmit={handleSubmit} className="space-y-6">
        {/* Nom complet */}
        <InputField
          name="nom"
          label="Nom complet"
          placeholder="Jean Dupont"
        />

        {/* Email */}
        <InputField
          name="email"
          label="Adresse email"
          type="email"
          placeholder="jean.dupont@email.com"
        />

        {/* Téléphone */}
        <InputField
          name="telephone"
          label="Numéro de téléphone"
          type="tel"
          placeholder="01 23 45 67 89"
        />

        {/* Adresse */}
        <InputField
          name="adresse"
          label="Adresse de livraison"
          type="textarea"
          placeholder="123 Rue de la Pizza, 75001 Paris"
          rows={4}
        />

        {/* Informations supplémentaires */}
        <div className="bg-gray-50 p-4 rounded-lg">
          <h3 className="font-semibold text-gray-700 mb-2 flex items-center">
            <span className="mr-2">ℹ️</span>
            Informations importantes
          </h3>
          <ul className="text-sm text-gray-600 space-y-1">
            <li>• Vos données sont sécurisées et ne seront pas partagées</li>
            <li>• Notre livreur vous contactera avant la livraison</li>
            <li>• Temps de livraison estimé : 30-45 minutes</li>
            <li>• Paiement à la livraison (espèces ou carte)</li>
          </ul>
        </div>

        {/* Bouton de soumission */}
        <button
          type="button"
          onClick={onSubmit}
          disabled={loading}
          className={`w-full py-4 rounded-lg font-semibold text-lg transition-all duration-200 flex items-center justify-center ${
            loading
              ? 'bg-gray-400 text-gray-700 cursor-not-allowed'
              : 'bg-green-500 hover:bg-green-600 text-white hover:shadow-lg active:scale-95'
          }`}
        >
          {loading ? (
            <>
              <span className="animate-spin mr-2">⏳</span>
              Commande en cours...
            </>
          ) : (
            <>
              <span className="mr-2">🚀</span>
              Confirmer la commande
            </>
          )}
        </button>
      </div>
    </div>
  );
};

export default OrderForm;