// const axios = require('axios');
//
// const reportStatus = async (status) => {
//     const apiKey = 'WKc9XeKavCgYQP5NkpEMBM98';
//     const monitorId = '2455345';
//
//     try {
//         await axios.post(`https://uptime.betterstack.com/api/v1/monitors/${monitorId}/heartbeat`, null, {
//             headers: {
//                 'Authorization': `Bearer ${apiKey}`,
//                 'Content-Type': 'application/json'
//             },
//             params: {
//                 status: status
//             }
//         });
//     } catch (error) {
//         console.error('Failed to report status to BetterStack:', error);
//     }
// };
//
// const status = process.argv[2] || 'up';  // Default to 'up'
// reportStatus(status);
