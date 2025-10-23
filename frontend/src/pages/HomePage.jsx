import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import { api } from '../services/api.js';

const HomePage = () => {
    const [products, setProducts] = useState([]);
    const [q, setQ] = useState('');
    const [category, setCategory] = useState('');

    useEffect(() => {
        const fetchProducts = async () => {
            try {
                const response = await api.get(`/products?q=${q}&category=${category}`);
                setProducts(response.content);
            } catch (error) {
                console.error('Error fetching products:', error);
                alert('Error fetching products');
            }
        };

        fetchProducts();
    }, [q, category]);

    return (
        <div>
            <h1>Product Catalog</h1>
            <div>
                <input
                    type="text"
                    placeholder="Search..."
                    value={q}
                    onChange={(e) => setQ(e.target.value)}
                />
                <input
                    type="text"
                    placeholder="Category..."
                    value={category}
                    onChange={(e) => setCategory(e.target.value)}
                />
            </div>
            <div>
                {products.map((product) => (
                    <div key={product.id}>
                        <Link to={`/product/${product.id}`}>
                            <h2>{product.name}</h2>
                        </Link>
                        <p>{product.price}</p>
                    </div>
                ))}
            </div>
        </div>
    );
};

export default HomePage;
